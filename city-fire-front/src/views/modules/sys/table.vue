<template>
<div>
  <!-- 选择表名下拉框 -->
  <el-select v-model="tablename" @change="getFields(tablename)" filterable placeholder="请选择表名"> 
    <el-option v-for="item in tables" :key="item.tablename" :label="item.comments" :value="item.tablename">
    </el-option>
  </el-select>

  <!-- 条件选择按钮 -->
  <el-button class="bon" @click="addCondition" type="primary" plain>条件添加</el-button>
  <el-button class="bon" @click="addOrderby" type="primary" plain>排序</el-button>
  <el-button class="bon"  type="primary" plain>查询</el-button>
  <br />

  <!-- 显示条件 -->
  <el-tag class="conditionstyle"
        v-for="con in conditionsArray"
        :key="con.field"
        closable
        type="danger"
        @close="handleClose(con)">
        {{con.field}}
  </el-tag>

  <!-- 全选按钮 -->
  <div class="allchoice"></div>
  <el-checkbox v-if="dis" v-model="checkAll" @change="handleCheckAllChange" border>全选</el-checkbox>

  <!-- 查询字段多选框 -->
  <el-checkbox-group id="choice" :min="1" v-model="checkedFields" @change="handleCheckedFieldsChange(checkedFields)">
    <el-checkbox class="box" v-for="item in fields" :key="item.field" :label="item.comments" :value="item.field" border></el-checkbox>
  </el-checkbox-group>

  <!--弹窗 添加条件组件 -->
  <table-add-condition v-if="addConditionVisible" v-on:commitConditions="commitConditions" ref="tableAddCondition"></table-add-condition>

  <!--弹窗 排序组件 -->
  <table-add-orderby v-if="addOrderbyVisible"  ref="tableAddOrderby"></table-add-orderby>

</div>
</template>
<script>
import TableAddCondition from './table-add-condition'
import TableAddOrderby from './table-add-orderby'

export default {

    data() {
      return {
        tables:[],
        //要获取字段的表名
        tablename:'',
        //中间字段
        fieldsinfo:[],
        // 是否为全选和全不选
        checkAll:false,
        //选中的字段
        checkedFields:[],
        //获取到查询的字段
        fields: [],

        conditionsArray:[],
        addConditionVisible:false,
        addOrderbyVisible:false,
        dis:false,
        //排序
        ranks: [
            {field:'',type:''},
            {field:'',type:''}
        ],
      }
    },
    activated() {
      this.getTables()
    },
    watch: {
      'tablename':function(){
        if (this.checkAll===true) {
          this.checkAll=false;
        }
      }
    },
    components:{
      TableAddCondition,
      TableAddOrderby
    },
    methods: {
      hidebox(){
        if(this.tablename!=''){
          this.dis=true;
        }
      },
      //页面添加条件click事件
      addCondition(){
        this.addConditionVisible=true;
        this.$nextTick(() => {
        this.$refs.tableAddCondition.init(this.fields)
       })
      },
      
      //页面添加排序
      addOrderby(){
        this.addOrderbyVisible=true;
        this.$nextTick(() => {
        this.$refs.tableAddOrderby.initOrder(this.fields)
       })
      },
      
      //删除条件事件
      handleClose(tag){
        this.tags.splice(this.tags.indexOf(tag),1);
      },

      //请求接口获取表名
      getTables(){
        this.$http.get('https://www.fastmock.site/mock/2da6b9d9348f0b439291ea82f405a722/info/getTable').then((response) =>{
              console.log(response.data)
              var result = response.data
              if (result.code==="200") {
                  this.tables=result.data;
                  console.log("-----表查询成功-----")
              }else{
                  console.log("-----失败-----")
              }							
					})
      },

      //请求接口 获取字段
      getFields(tablename){
        this.$http.get('https://www.fastmock.site/mock/2da6b9d9348f0b439291ea82f405a722/info/getField/'+tablename).then((response) =>{
              console.log(response.data)
              var result = response.data
              if (result.code==="200") {
                 this.fields = result.data.fields;
                 console.log("-----字段查询成功-----");
                 this.checkedFields=[];
                 this.checkedFields[0] = this.fields[0].comments;
                 this.hidebox();
              }else{
                  console.log("-----失败-----")
              }							
					})
      },

      //触发全选全部不选 初始值为 false 全不选
      handleCheckAllChange(){
        //将fields是一个数组对象 我们只需要对象中的Nodes字段 赋值给 fieldsinfo数组 
        for (const key in this.fields) {
            this.fieldsinfo[key] = this.fields[key].comments
        }
        //如果true未全选将所有的注释赋值给checkedFields数组 checkedFields是被选中的框中的值数组 为flase就选中第一个框 
        //三元运算符 为true就选中第一个 fieldsinfo 数组传递给 checkedFields 为全选 为flase就选中第二个 fieldsinfo数组中的索引为0的传递给checkedFields 选中索引为0的一个框
        this.checkedFields = this.checkAll ? this.fieldsinfo : [this.fieldsinfo[0]];
        //根据选中的注释获取数组对象中相对的字段
        this.fieldSys();
      },

      //点击选中框改变事件 
      handleCheckedFieldsChange(checkedFields){
          //获取checkedFields的长度 也是就 选中框的个数
          var checkedCount = checkedFields.length;
          //判断是否相等 返回false或true赋值给checkAll 等于则全选按钮为true 否则为false
          this.checkAll = checkedCount === this.fields.length;
          this.fieldSys()
      },

      //获取到所需要的字段
      fieldSys(){
        this.cos=[];
        if (this.checkedFields!=null) {
          var fieldarr = this.checkedFields
          for (const key in this.fields) {
              var temp =this.fields[key];
              for (const key2 in fieldarr) {
                if (temp.comments===fieldarr[key2]) {
                  //输出相对的字段 而不是注释
                  this.cos[key2]=temp.field.toString()
              }
            }
          }
          console.log("表名:"+this.tablename+"字段:"+this.cos)
          console.log("-----------")
        }
      },
      
      //获取条件框提交的值
      commitConditions(conditions) {
        console.log('调用')
        console.log(JSON.stringify(conditions))
        var tmp = JSON.stringify(conditions)
        tmp = JSON.parse(tmp)
        this.conditionsArray.push(tmp)
        console.log(this.conditionsArray)
      }
   }
 }
</script>

<style scoped>
.bon{
    margin-left: 10px;
}

 .conditionstyle{
  margin-top: 15px;
  margin-left: 5px;
} 

.allchoice{ 
    margin-bottom: 10px;    
}

#choice{
  display: inline;
}

.boxs{
    margin: 5px;
}
</style>