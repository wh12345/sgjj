<template>
    <div id="bdMap1" style="width: 100%; height: 500px;"></div>
</template>
<script>
    import '../../../../static/map/map_loader.js'
    let map 
    export default {
        data() {
            return{
              locations:[],
              interval: ''     
            }
        },
        mounted () {
            this.$nextTick(() => {
                this.drawMap()           
            })
            //setInterval(this.getLocation,180000)
            this.interval = setInterval(this.getLocation,10000)       
        },
        beforeDestroy() {    //页面关闭时清除定时器  
            clearInterval(this.interval)
        },
        methods: {
            drawMap () {
                map = new window.BMap.Map('bdMap1', {})
                map.setCurrentCity('韶关市武江区')
                map.centerAndZoom(new window.BMap.Point(113.564198, 24.740024), 12)
                map.enableScrollWheelZoom()
                map.enableContinuousZoom();	
                // 如下两项推荐设置，否则没有对应级别的瓦片会出现空白
                map.setMinZoom(11)
                map.setMaxZoom(19)
                // 添加带有定位的导航控件
                var navigationControl = new BMap.NavigationControl({
                    // 靠左上角位置
                    anchor: BMAP_ANCHOR_TOP_LEFT,
                    // LARGE类型
                    type: BMAP_NAVIGATION_CONTROL_LARGE,
                    // 启用显示定位
                    enableGeolocation: true
                });
                map.addControl(navigationControl)  
                this.getLocation()
                //请求数据
                /*//行政区划
                this.$http.get('http://localhost:8888/static/map/boundaries/hd.json').then((res) => {         
                    let data = JSON.parse(JSON.stringify(res.data))
                    let boundaries = data.boundaries
                    let pointArray = []
                    for (var i = 0; i < boundaries.length; i++) {
                        let ply = new window.BMap.Polygon(boundaries[i], {
                            strokeWeight: 2,
                            strokeOpacity: 1,
                            strokeStyle: 'solid',
                            strokeColor: '#ff0000',
                            fillColor: '#00ffff',
                            fillOpacity: 0.001
                        })
                        map.addOverlay(ply)
                        pointArray = pointArray.concat(ply.getPath())
                    }
                    map.setViewport(pointArray)
                    })*/
                
            },
            getLocation() {   
                console.log('定位刷新任务执行----')
                this.deleteAllOverlay()                            
                this.$http({
                    url: this.$http.adornUrl('/tq/location/getlocation'),
                    method: 'get',
                    params: this.$http.adornParams()
                }).then(({data}) => {
                    if(data.code=='0') {
                        this.locations = data.locations                     
                        for(let i in this.locations) {
                            let location = this.locations[i];                          
                            var longitude = location.longitude
                            var latitude  = location.latitude
                            var hphm = location.HPHM
                            var content = "姓&emsp;&emsp;名:"+location.JYXM+"<br/>警&emsp;&emsp;号:"+location.JYBH+
                                  "<br/>车牌号码:"+location.HPHM+"<br/>电话号码:"+location.PHONENUMBER+
                                  "<br/>位置信息:"+longitude+","+latitude
                            var point = new window.BMap.Point(longitude, latitude);
                            var marker = new window.BMap.Marker(point);  // 创建标注
                            map.addOverlay(marker);               // 将标注添加到地图中
                            this.addClickHandler(content,marker,hphm);
                            //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                        }
                    }else{
                        this.$notify.info({
                            title: '消息',
                            message: '暂无铁骑最新定位数据'
                        })
                    }
                })
            },
            addClickHandler(content,marker,hphm){
                var opts = {
                        width : 260,     // 信息窗口宽度
                        height: 105,     // 信息窗口高度
                        title : "铁骑信息" , // 信息窗口标题
                        enableMessage:true,//设置允许信息窗发送短息
                        message:"铁骑信息"
                }
                var label = new BMap.Label(hphm,{offset:new window.BMap.Size(-10,-15)})
                //设置label样式
                label.setStyle({
                    color : "skyblue",
                    fontSize : "12px",
                    height : "20px",
                    fontFamily:"微软雅黑",
                    maxWidth:"none",
                    border: 0,
                    background: 0
                })
                marker.setLabel(label)
                marker.addEventListener("click",function(e){
                    var p = e.target
                    var point = new window.BMap.Point(p.getPosition().lng, p.getPosition().lat)
                    var infoWindow = new window.BMap.InfoWindow(content,opts)  // 创建信息窗口对象 
                    map.openInfoWindow(infoWindow,point) //开启信息窗口
                })
            },
            deleteAllOverlay() {
                if(this.locations.length>0) {
                    var allOverlay = map.getOverlays()               
                    for (var i = 0; i < allOverlay.length -1; i++){
                         map.removeOverlay(allOverlay[i])                       
                    }                   
                }   
            }
        }
    }
</script>