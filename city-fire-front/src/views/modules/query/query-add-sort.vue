<template>
<!-- 条件弹框  -->
<el-dialog  title="升序降序" :close-on-click-modal="false" :visible.sync="visibleSort">
    <div id="main_sort">
      <!-- 条件字段 -->
      <div class="div">
        <div class="div2">
        条件字段:
        </div>
        <el-select @change="changeField" style="width:250px" v-model="sort.field" filterable placeholder="请选择条件字段"> 
          <el-option v-for="item in fields" :key="item.field" :label="item.comments" :value="item.field">
          </el-option>
        </el-select>
      </div>

      <div class="div">
        <div class="div2">
        选择排序:
        </div>
          <el-radio-group :disabled="prohibitSort" v-model="sort.value">
            <el-radio v-for="item in sortType" :key="item.type" :label="item.type" border>{{item.comments}}</el-radio>
          </el-radio-group>
      </div> 

      <!-- 显示排序字段 -->
      <el-tag id="tag"
        v-for="so in sorts"
        :key="so.field"
        closable
        type="danger"
        @close="handleClose(so)">
        {{so.field+" "+so.value}}
      </el-tag>
    </div>
    
    <!-- 按钮 -->
    <div slot="footer" class="dialog-foot er">
      <!-- 添加升序降序 按钮 -->
      <el-button class="bon" @click="addAOrderby()" type="primary" plain>添加</el-button>
      <el-button @click="cancel()">取 消</el-button>
      <el-button type="primary"  @click="submission()">提交</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
    data(){
      return{
          visibleSort:false,
          fields:[],
          prohibitSort:false,
          sort:{
            field:'',
            value:'',
          },
          sortType:[{type:'asc',comments:'默认升序'},
                    {type:'desc',comments:'降序'}    
          ],
          sorts:[]
        }
    },
    watch: {
      'sort.field':function(){
         this.sort.value='asc'
      },
      'visibleSort':function(){
         this.initcolose()
      }
    },
    methods: {
      //初始化方法
      initShowSort(fields) {
        this.visibleSort = true
        this.fields = fields
        this.$nextTick(() => {})
      },

      //changeField字段改变事件
      changeField(){
        this.prohibitSort=false;
        for (const key in this.sorts) {
          if (this.sort.field === this.sorts[key].field) {
            this.prohibitSort=true
          }
        }
          console.log("SDFS:"+JSON.stringify(this.sorts))
      },

      //添加排序
      addAOrderby(){
        if (this.prohibitSort===true) {
          this.$message.error('此字段已存在!');
          return
        }
        if(this.sort.field===''){
          this.$message('请选择字段!');
          return
        }
        console.log(this.sort.field)
        var tmp = JSON.stringify(this.sort);
        tmp = JSON.parse(tmp);
        console.log(JSON.stringify(tmp))
        this.sorts.push(tmp)
        this.changeField()
      },

      //确定事件
      submission(){
        alert("操作成功！")
        this.visibleSort = false;
        this.tagsNoe = this.tags; 
      },

      //取消初始化
      initcolose(){
        this.sort.field=''
        this.sort.value=''
        this.sorts=[]
      },

      //取消事件
      cancel(){
        this.visibleSort = false
        this.initcolose()
      },
      
      //删除
      handleClose(so){
        this.sorts.splice(this.sorts.indexOf(so),1);
        this.changeField()
      }
    }
 }
</script>

<style scoped>
.bon{
    margin-left: 10px;
}

#main_sort{
  height: 200px;
  margin: auto;
  width: 350px;
}

.div{
  margin-bottom: 10px;
}
.div2{
  display: inline-block;
  width: 60px; 
  font-family:"微软雅黑";
}

#tag{
  margin-right: 10px; 
  margin-bottom: 10px;
}
</style>