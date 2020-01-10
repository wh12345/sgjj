<template>
    <div id="bdMap2" style="width: 100%; height: 500px;"></div>
</template>
<script>
    import '../../../../static/map/map_loader.js'
    let map 
    export default {
        data() {
            return{
                infoWindow:''    
            }
        },
        mounted () {
            this.$nextTick(() => {
                this.drawMap()           
            }) 
        },
        methods: {
            drawMap () {
                map = new window.BMap.Map('bdMap2', {})
                map.setCurrentCity('韶关市武江区')
                map.centerAndZoom(new window.BMap.Point(113.564198, 24.740024), 12)
                map.enableScrollWheelZoom()
                map.enableContinuousZoom();	
                // 如下两项推荐设置，否则没有对应级别的瓦片会出现空白
                map.setMinZoom(11)
                map.setMaxZoom(19) 
                 // 添加带有定位的导航控件
                var navigationControl = new window.BMap.NavigationControl({
                    // 靠左上角位置
                    anchor: BMAP_ANCHOR_TOP_LEFT,
                    // LARGE类型
                    type: BMAP_NAVIGATION_CONTROL_LARGE,
                    // 启用显示定位
                    enableGeolocation: true
                });
                map.addControl(navigationControl)
                map.addEventListener("click", this.showInfo);  
            },
            showInfo(e) {
                var lng = e.point.lng
                var lat = e.point.lat
                var point = new window.BMap.Point(lng,lat)
                var opts = {
                    width : 280,     // 信息窗口宽度
                    height: 205,     // 信息窗口高度
                    title : "警情发布" , // 信息窗口标题
                    enableMessage:true//设置允许信息窗发送短息
                }
                var inputstyle ="box-sizing: border-box;text-align:left;font-size:0.4em;height:30px;width:200px;border-radius:3px;border:1px solid #c8cccf;color:#6a6f77;-web-kit-appearance:none;-moz-appearance: none;outline:0;padding:0 1em;text-decoration:none;"                
                var content = "定&nbsp;位&nbsp;信&nbsp;息:经度-"+lng+" 纬度-"+lat+"<br/>"
                content = content+"<div style='padding-top: 3px;'>警&nbsp;情&nbsp;描&nbsp;述:<input type='text' id='describe' value='' style='"+inputstyle+"' placeholder='请输入警情描述'/><br/></div>"
                content = content+"<div style='padding-top: 3px;'>详&nbsp;细&nbsp;地&nbsp;址:<input type='text' id='address' value=''  style='"+inputstyle+"'  placeholder='请输入详细地址'/><br/></div>"
                content = content+"<div style='padding-top: 3px;'>联系人姓名:<input type='text' id='linkman' value=''  style='"+inputstyle+"'  placeholder='请输入联系人姓名'/><br/></div>"
                content = content+"<div style='padding-top: 3px;'>联系人电话:<input type='text' id='phonenumber' value=''  style='"+inputstyle+"'  placeholder='请输入联系人电话'/><br/></div>"
                content = content+"<div style='width: 280px;border: 1px solid none;padding-left: 80px;padding-top: 10px;'>"
                content = content+"<input type='button' id='cancle' value='取消'  style='color:skyblue;background-color:#17B3A3'/>&emsp;"
                content = content+"<input type='button' id='publish' value='发布' style='color:skyblue;background-color:#17B3A3'/></div>"
                this.infoWindow = new window.BMap.InfoWindow(content, opts);  // 创建信息窗口对象 
                map.openInfoWindow(this.infoWindow,point)
                let that = this                
                if(!this.infoWindow.isOpen()) {                   
                    this.infoWindow.addEventListener("open",function(e){
                        var cancle = document.getElementById("cancle")
                        var publish = document.getElementById("publish")
                        cancle.onclick =function() {
                            map.closeInfoWindow(this.infoWindow,point)
                        }
                        publish.onclick =function() {
                            var describe = document.getElementById("describe").value   
                            var address = document.getElementById("address").value     
                            var linkman = document.getElementById("linkman").value     
                            var phonenumber = document.getElementById("phonenumber").value  
                            var info ={"lng":lng,"lat":lat,"describe":describe,"address":address,"linkman":linkman,"phonenumber":phonenumber} 
                            that.publish(info)
                        }
                    })
                }else{
                    var cancle = document.getElementById("cancle")
                    var publish = document.getElementById("publish")
                    var describe = document.getElementById("describe").value   
                    var address = document.getElementById("address").value     
                    var linkman = document.getElementById("linkman").value     
                    var phonenumber = document.getElementById("phonenumber").value 
                    var info ={"lng":lng,"lat":lat,"describe":describe,"address":address,"linkman":linkman,"phonenumber":phonenumber}                
                    cancle.onclick =function() {
                        map.closeInfoWindow(this.infoWindow,point)
                    }
                    publish.onclick =function() {
                        var describe = document.getElementById("describe").value   
                        var address = document.getElementById("address").value     
                        var linkman = document.getElementById("linkman").value     
                        var phonenumber = document.getElementById("phonenumber").value  
                        var info ={"lng":lng,"lat":lat,"describe":describe,"address":address,"linkman":linkman,"phonenumber":phonenumber} 
                        that.publish(info)
                    }
                }
            },
            publish(info) {
                console.log(info)
                this.$http({
                    url: this.$http.adornUrl('/tq/dispatch/publish'),
                    method: 'get',
                    params: this.$http.adornParams(info)
                }).then(({data}) => {
                    if(data.code=='0') {                   
                        var longitude = data.cavalry.longitude
                        var latitude =  data.cavalry.latitude
                        var hphm = data.cavalry.hphm
                        var jyxm = data.cavalry.jyxm
                        var phonenumber = data.cavalry.phonenumber 
                        console.log(data.cavalry)
                        var sy = new window.BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
                            scale: 0.6,//图标缩放大小
                            strokeColor:'#fff',//设置矢量图标的线填充颜色
                            strokeWeight: '2',//设置线宽
                        });
                        var icons = new window.BMap.IconSequence(sy, '10', '30')
                        // 创建polyline对象
                        var pois = [
                            new window.BMap.Point(longitude,latitude),
                            new window.BMap.Point(info.lng,info.lat)
                        ];
                        var polyline =new window.BMap.Polyline(pois, {
                            enableEditing: false,//是否启用线编辑，默认为false
                            enableClicking: true,//是否响应点击事件，默认为true
                            icons:[icons],
                            strokeWeight:'8',//折线的宽度，以像素为单位
                            strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
                            strokeColor:"#18a45b" //折线颜色
                        })
                        map.addOverlay(polyline)  //增加折线
                        map.closeInfoWindow(this.infoWindow,new window.BMap.Point(info.lng,info.lat))
                        var marker = new window.BMap.Marker(new window.BMap.Point(longitude,latitude))// 创建标注
                        map.addOverlay(marker)// 将标注添加到地图中
                        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画  
                        var content = "警&nbsp;&nbsp;&nbsp;员:"+jyxm+"<br/>车牌号:"+hphm+"<br/>电&nbsp;&nbsp;&nbsp;话:"+phonenumber
                        var label = new BMap.Label(content,{offset:new window.BMap.Size(-40,-60)})
                        //设置label样式
                        label.setStyle({
                            color : "red",
                            fontSize : "12px",
                            height : "20px",
                            fontFamily:"微软雅黑",
                            maxWidth:"none",
                            border: 0,
                            background: 0
                        })
                        marker.setLabel(label)                                      
                        var vectorMarker = new window.BMap.Marker(new BMap.Point(info.lng,info.lat), {
                            // 指定Marker的icon属性为Symbol
                            icon: new window.BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
                                scale: 1.3,//图标缩放大小
                                fillColor: "red",//填充颜色
                                fillOpacity: 0.8//填充透明度
                            })
                        })
                        map.addOverlay(vectorMarker)   
                        vectorMarker.show()
                        var details = "警情描述:"+info.describe+"<br/>处理状态:处理中"
                        var label1 = new BMap.Label(details,{offset:new window.BMap.Size(-23,-33)});
                        //设置label样式
                        label1.setStyle({
                            color : "red",
                            fontSize : "12px",
                            height : "20px",
                            fontFamily:"微软雅黑",
                            maxWidth:"none",
                            border: 0,
                            background: 0
                        })
                        vectorMarker.setLabel(label1)                   
                    }else{
                        this.$notify.info({
                            title: '消息',
                            message: '网络异常,请稍后再试!'
                        })
                    }
                })
            }
        }   
    }
</script>