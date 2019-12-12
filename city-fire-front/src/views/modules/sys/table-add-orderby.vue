<template>
<!-- 条件弹框  -->
<el-dialog  title="升序降序" :close-on-click-modal="false" :visible.sync="visiblesort">
    <div id="orderby">
      <!-- 条件字段 -->
      <div class="div">
        <div class="div2">
        条件字段:
        </div>
        <el-select @change="changeField" style="width:250px" v-model="sort.fieldvalue" filterable placeholder="请选择条件字段"> 
          <el-option v-for="item in fields" :key="item.field" :label="item.comments" :value="item.field">
          </el-option>
        </el-select>
      </div>

      <div class="div">
        <div class="div2">
        选择排序:
        </div>
          <el-radio-group :disabled="sortprohibit" v-model="sort.ordervalue">
            <el-radio v-for="item in orderbys" :key="item.type" :label="item.type" border>{{item.comments}}</el-radio>
          </el-radio-group>
      </div> 


      <!-- 显示排序字段 -->
      <el-tag class="tagcls"
        v-for="so in sorts"
        :key="so.text"
        closable
        type="danger"
        @close="handleClose(so)">
        {{so.fieldvalue+" "+so.ordervalue}}
      </el-tag>
    </div>
    
    <!-- 按钮 -->
    <div slot="footer" class="dialog-foot er">
      <!-- 添加升序降序 按钮 -->
      <el-button class="bon" @click="addAOrderby" type="primary" plain>添加</el-button>
      <el-button @click="cancel()">取 消</el-button>
      <el-button type="primary"  @click="news()">提交</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
    data(){
      return{
          visiblesort:false,
          fields:[],
          tags:'',
          tagsNoe: [],
          sortprohibit:false,
          sort:{
            fieldvalue:'',
            ordervalue:'',
          },
          orderbys:[{type:'asc',comments:'默认升序'},
                   {type:'desc',comments:'降序'}
                    
          ],
          sorts:[]
        }
    },
    watch: {
      'sort.fieldvalue':function(){
         this.sort.ordervalue='asc'
      },
      'visiblesort':function(){
         this.initcolose()
      }
    },
    methods: {
      //初始化方法
      initOrder(fields) {
        this.visiblesort = true
        this.fields = fields
        this.$nextTick(() => {})
      },

      //changeField字段改变事件
      changeField(){
        for (const key in this.sorts) {
          if (this.sort.fieldvalue === this.sorts[key].fieldvalue) {
            this.sortprohibit=true;
            return
          }else{
            this.sortprohibit=false;
          }
        }
      },

      //添加排序
      addAOrderby(){
        if (this.sortprohibit===true) {
          this.$message.error('此字段已存在!');
          return
        }
        if(this.sort.fieldvalue===''){
          this.$message('请选择字段!');
          return
        }
        console.log(this.sort.fieldvalue)
        var tmp = JSON.stringify(this.sort);
        tmp = JSON.parse(tmp);
        console.log(JSON.stringify(tmp))
        this.sorts.push(tmp)
        this.changeField()
      },

      //确定事件
      news(){
        alert("操作成功！")
        this.visibleorderby = false;
        this.tagsNoe = this.tags; 
      },

      //取消初始化
      initcolose(){
        this.sort.ordervalue=''
        this.sort.fieldvalue=''
        this.sorts=[]
      },

      //取消事件
      cancel(){
        this.visiblesort = false
        this.initcolose()
      },
      
      //删除
      handleClose(so){
        this.sorts.splice(so,1);
        this.changeField()
      }
    }
 }

</script>

<style scoped>
.bon{
    margin-left: 10px;
}

#orderby{
  height: 150px;
  margin: auto;
  width: 400px;
} 

.div{
  margin-bottom: 10px;
}

.div2{
  display: inline-block;
  width: 70px; 
}
</style>