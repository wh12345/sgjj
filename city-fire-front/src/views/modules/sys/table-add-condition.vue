<template>
<!-- 条件弹框  -->
<el-dialog  title="条件添加" :close-on-click-modal="false" :visible.sync="visible">
    <!-- 操作框 -->
    <div id="where"> 
      <!-- 条件字段 -->
      <div class="div">
        <div class="div2">
        条件字段:
        </div>
        <el-select style="width:400px" @change="changeField()" v-model="condition.field" filterable placeholder="请选择条件字段"> 
          <el-option v-for="item in fields" :key="item.field" :label="item.comments" :value="item.field">
          </el-option>
        </el-select>
      </div>

      <!-- DATE 类型 -->
      <div v-show="showDate">
        <div class="div">
          <div class="div2">
          运 算 符:
          </div>
          <el-select :disabled="dateprohibit" @change="changeDateSymbol" style="width:400px" v-model="condition.symbol" filterable placeholder="请选择操作符"> 
            <el-option v-for="item in opts" :key="item.symbol" :label="item.comments" :value="item.symbol"></el-option>
          </el-select>
        </div>

        <div v-show="showdatechoice.showframe" class="div">
            <div class="div2">
            格 式:
            </div>
             <el-radio-group  :disabled="dateprohibit" @change="changeDateFormat" v-model="condition.dateformat">
                <el-radio :value="dateformattype[0].format" :label="dateformattype[0].format">{{dateformattype[0].comments}}</el-radio>
                <el-radio :value="dateformattype[1].format" :label="dateformattype[1].format">{{dateformattype[1].comments}}</el-radio>
                <el-radio :value="dateformattype[2].format" :label="dateformattype[2].format">{{dateformattype[2].comments}}</el-radio>
             </el-radio-group>
        </div>

        <div>
          <div v-show="showdatechoice.showmonthframe0" class="div" >
            <div class="div2">
                  月 份:
                  </div>
                  <el-date-picker
                        v-model="condition.datevalue1"
                        type="month"
                        placeholder="选择月份"
                        value-format="yyyy-MM"
                        :disabled="dateprohibit">
                  </el-date-picker>
            </div>

            
            <div v-show="showdatechoice.showdayframe0" class="div">
              <div class="div2">
                  日 期:
                  </div>
                  <el-date-picker
                      v-model="condition.datevalue1"
                      type="date"
                      placeholder="选择日期"
                      value-format="yyyy-MM-dd"
                      :disabled="dateprohibit">
                  </el-date-picker>
              </div>

            <div v-show="showdatechoice.showtimeframe0" class="div">
              <div class="div2">
                  时 间:
                  </div>
                  <el-date-picker 
                      v-model="condition.datevalue1"
                      type="datetime"
                      placeholder="选择时间"
                      value-format="yyyy-MM-dd hh:mm:ss"
                      default-time="12:00:00"
                      :disabled="dateprohibit">
                  </el-date-picker>
            </div>
        </div>

        <div v-show="showdatechoice.showmonthframe1" class="div">
            <div class="div2">
            月 份:
            </div>
              <el-checkbox v-model="checkedbtmin"></el-checkbox>
                <el-date-picker
                  v-model="condition.datevalue2"
                  type="monthrange"
                  range-separator="————"
                  start-placeholder="开始月份"
                  end-placeholder="结束月份"
                  value-format="yyyy-MM"
                  :disabled="dateprohibit">
                </el-date-picker>
              <el-checkbox v-model="checkedbtmax"></el-checkbox>
              <div class="divicon">
                <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
              </div>
          </div>
          
          <div v-show="showdatechoice.showdayframe1" class="div">
            <div class="div2">
            日 期:
            </div>
                <el-checkbox v-model="checkedbtmin"></el-checkbox>
                <el-date-picker
                  v-model="condition.datevalue2"
                  type="daterange"
                  range-separator="————"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd"
                  :disabled="dateprohibit">
                </el-date-picker>
                <el-checkbox v-model="checkedbtmax"></el-checkbox>
                <div class="divicon">
                  <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
                </div>
            </div>

            <div v-show="showdatechoice.showtimeframe1" class="div">
              <div class="div2">
                时 间:
                </div>
                <el-checkbox v-model="checkedbtmin"></el-checkbox>
                <el-date-picker
                  v-model="condition.datevalue2"
                  type="datetimerange"
                  range-separator="————"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  value-format="yyyy-MM-dd hh:mm:ss"
                  :disabled="dateprohibit">
                </el-date-picker>
                <el-checkbox v-model="checkedbtmax"></el-checkbox>
                <div class="divicon">
                  <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
                </div>
            </div>
      </div>

      <!-- VARCHAR2 类型 -->
      <div v-show="showVar">
          <div class="div">
            <div class="div2">
            运 算 符:
            </div>
              <el-radio-group @change="changeVarSymbol" v-model="condition.symbol">
                <el-radio :disabled="varprohibit" v-for="item in opts" :key="item.symbol" :label="item.symbol" >{{item.comments}}</el-radio>
              </el-radio-group>
          </div>  
          <div v-show="showvarinput" class="div">
            <div class="div2">
            操 作 符:
            </div>
              <el-radio-group :disabled="varprohibit"  v-model="lookup">
                <el-radio v-for="item in lookupdate" :key="item.mode" :label="item.mode" >{{item.comments}}</el-radio>
              </el-radio-group>
          </div>  
        <div v-show="showvarinput" class="div">
          <div class="div2">
          条件内容:
        </div>
            <el-input clearable :disabled="varprohibit" style="width:400px" v-model="condition.textvalue" placeholder="请输入条件内容为字符串类型"></el-input>
        </div>
      </div>
      
      <!-- NUMBER 类型 -->
      <div v-show="showNum">
        <div class="div">
          <div class="div2">
          运 算 符:
          </div>
          <el-select :disabled="numprohibit" @change="changeNumSymbol" style="width:400px" v-model="condition.symbol" filterable placeholder="请选择操作符"> 
            <el-option v-for="item in opts" :key="item.symbol" :label="item.comments" :value="item.symbol"></el-option>
          </el-select>
        </div>
        <div v-show="shownuminput" class="div">
            <div class="div2">
            条件内容:
            </div>
            <el-input :disabled="numprohibit" clearable style="width:400px" v-model="condition.numvalue1" placeholder="请输入条件内容为数字类型"></el-input>
        </div>
        <div v-show="showsection">
          <div class="div">
            <div class="div2">
            区间选择:
            </div>
            <el-checkbox :disabled="numprohibit" v-model="checkedbtmin"></el-checkbox>
            <el-input :disabled="numprohibit" style="width:170px" v-model="condition.numvalue1" placeholder=""></el-input>
            <div class="divnum">
              <font class="el-icon-minus"></font>
            </div>
            <el-input :disabled="numprohibit" style="width:170px" v-model="condition.numvalue2" placeholder=""></el-input>
            <el-checkbox :disabled="numprohibit" v-model="checkedbtmax"></el-checkbox>
            <div class="divicon">
              <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
            </div>
          </div>  
        </div>
      </div>

      <!-- 显示条件 -->
      <el-tag id="tag"
        v-for="tag in tags"
        :key="tag.field"
        closable
        @close="handleClose(tag)">
        {{tag.field+" "+tag.symbol+" "+tag.value}}
      </el-tag>
    </div>
    
    <!-- 按钮 -->
    <div slot="footer" class="dialog-foot er">
      <!-- 条件添加 按钮 -->
      <el-button class="bon" @click="addACondition" type="primary" plain>添加</el-button>
      <!-- 取消添加 按钮 -->
      <el-button @click="cancel()">取 消</el-button>
      <!-- 条件提交 按钮 -->
      <el-button type="primary" v-on:click="news()">提交条件</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
    data(){
      return{
          visible:false,
          fields:[],
          opts:[],
          tags:[],
          // DATE 隐藏显示 V-if
          showdatechoice:{
            showframe:true,
            showmonthframe0:false,
            showdayframe0:false,
            showtimeframe0:false,
            showmonthframe1:false,
            showdayframe1:false,
            showtimeframe1:false
          },
          // DATE 多选框的值
          dateformattype:[{format:'YYYY-MM',comments:'yyyy-MM'},
                         {format:'YYYY-MM-DD',comments:'yyyy-MM-dd'},
                         {format:'YYYY-MM-DD HH:24:MI:SS',comments:'yyyy-MM-dd hh:mm:ss'}
          ],
          //区间 选择按钮
          checkedbtmin:false,
          checkedbtmax:false,
          //禁用
          varprohibit:false,
          numprohibit:false,
          dateprohibit:false,
          //隐藏 显示 
          showVar:false,
          showNum:false,
          showDate:false,
          //NUMBER类型 中 文本和区间的 显示 隐藏 
          shownuminput:true,
          showsection:false,
          
          //VARVHAR类型 中文本显示
          showvarinput:true,
          //VARCHAR类型的单选框
          lookup:'',
          lookupdate:[{mode:'fxactquery',comments:'精确匹配'},
                      {mode:'fuzzyquery',comments:'模糊查询'}
          ],
          //传给后台的值
          condition:{            
            field:'',      //字段
            type:'',       //类型
            symbol:'',     //操作符
            textvalue:'',  //文本值
            numvalue1:'',  //数字值
            numvalue2:'',  //数字值
            dateformat:'', //返回时间类型 YYYY-MM-DD HH:24:MI:SS
            datevalue1:'', //时间值
            datevalue2:'', //时间值
          },
          conditions:[
          ],    
          //操作符    
          myoperators:{
            VARCHAR2:[
              {symbol:'=',comments:'等于'},
              {symbol:'<>',comments:'不等于'},
              {symbol:'IS NULL',comments:'为空'},
              {symbol:'IS NOT NULL',comments:'不为空'}],
            NUMBER:[                  
              {symbol:'=',comments:'等于'},
              {symbol:'<>',comments:'不等于'},
              {symbol:'<',comments:'大于'},
              {symbol:'>',comments:'小于'},
              {symbol:'<=',comments:'大于等于'},
              {symbol:'>=',comments:'小于等于'},
              {symbol:'IS NULL',comments:'为空'},
              {symbol:'IS NOT NULL',comments:'不为空'},
              {symbol:'bt',comments:'区间'}
            ],
            DATE:[
              {symbol:'=',comments:'等于'},
              {symbol:'<>',comments:'不等于'},
              {symbol:'<',comments:'大于'},
              {symbol:'>',comments:'小于'},
              {symbol:'<=',comments:'大于等于'},
              {symbol:'>=',comments:'小于等于'},
              {symbol:'IS NULL',comments:'为空'},
              {symbol:'IS NOT NULL',comments:'不为空'},
              {symbol:'bt',comments:'区间'}
            ],
           }
        }
    },
    watch: {
      'condition.field':function(){
            this.condition.type=''
            this.condition.textvalue=''

            this.condition.numvalue1=''
            this.condition.numvalue2=''
            this.shownuminput=true
            this.showvarinput=true
            this.showsection=false

            this.checkedbtmin=''
            this.checkedbtmax=''

            this.condition.datevalue1=''
            this.condition.datevalue2=''
            this.hideDate()
      },
      'visible':function(){
        if (this.visible==false) {
          this.initCondition()
        }
      }
    },
    methods: {
      //init初始化方法
      init(fields) {
        this.visible = true
        this.fields = fields
        this.$nextTick(() => {})
      },

      //add添加条件事件
      addACondition(){
        //条件字段判空
        if (this.condition.field ===''){
          this.$message('请选择字段!');
        //运算符判空
        }else if(this.condition.symbol ===''){
          this.$message('请选择操作符!');
        //条件内容判空 true & true &true
        }else if(this.condition.textvalue ==='' & this.condition.numvalue1==='' & this.condition.numvalue2==='' & this.condition.datevalue1==='' & this.condition.datevalue2==='' ){
          this.$message('条件内容不能为空!');
        }else{
          for (const key in this.fields) {
            if (this.fields[key].field===this.condition.field) {
               if(this.fields[key].type==='DATE'){
                if(this.dateprohibit===true){
                  this.$message.error('错误,此字段你已经添加!');
                  return;
                }
                this.condition.type="DATE"
                if (this.condition.symbol==="bt") {
                   if (this.checkedbtmin===false&this.checkedbtmax===false) {
                        this.condition.symbol="bt1"                       
                    }else if (this.checkedbtmin===false&this.checkedbtmax===true) {
                        this.condition.symbol="bt2"                       
                    }else if (this.checkedbtmin===true&this.checkedbtmax===false) {
                        this.condition.symbol="bt3"
                    }else if (this.checkedbtmin===true&this.checkedbtmax===true) {
                        this.condition.symbol="bt4"
                    }
                    this.condition.datevalue1=this.condition.datevalue2[0]
                    this.condition.datevalue2=this.condition.datevalue2[1]
                  }
                    this.showcondition()
               }else if (this.fields[key].type==='VARCHAR2') {
                  if(this.varprohibit===true){
                    this.$message.error('错误,此字段你已经添加!');
                    return;
                  }
                  this.condition.type='VARCHAR2';
                  if(this.lookup===''){
                    this.$message('请选择操作符!');
                    return ;
                  }
                  if (this.lookup==='fuzzyquery') {
                    if (this.condition.symbol==='=') {
                       this.condition.symbol='LIKE';
                    }else{
                       this.condition.symbol='NO LIKE';
                    }
                  }
                  this.condition.dateformat=''
                  this.showcondition()
               }else if (this.fields[key].type==='NUMBER') {
                 if(this.numprohibit===true){
                    this.$message.error('错误,此字段你已经添加!');
                    return;
                  }
                  this.condition.type="NUMBER";
                  if (this.condition.symbol==="bt") {
                    if (this.numvalue1>=this.numvalue2) {
                        alert()
                        this.$message('最小值不能大于等于最大值!');
                        return
                    } 
                    if (this.checkedbtmin===false&this.checkedbtmax===false) {
                        this.condition.symbol="bt1"                       
                      }else if (this.checkedbtmin===false&this.checkedbtmax===true) {
                          this.condition.symbol="bt2"                       
                      }else if (this.checkedbtmin===true&this.checkedbtmax===false) {
                          this.condition.symbol="bt3"
                      }else if (this.checkedbtmin===true&this.checkedbtmax===true) {
                          this.condition.symbol="bt4"
                      }
                  }
                  this.condition.dateformat=''
                  this.showcondition()
               }         
            }
          }
          this.initAdd();
        }
      },

      //field下拉选的change事件 选中条件字段的类型判断 展示出不一样的运算符和条件内容框
      changeField(){
         this.varprohibit=false;
         this.numprohibit=false;
         this.dateprohibit=false;
         for (const key in this.fields) {
           if(this.fields[key].field===this.condition.field){
              if(this.fields[key].type==='VARCHAR2'){
               this.opts = this.myoperators.VARCHAR2;
               this.showDate=false;
               this.showNum=false;
               this.showVar=true;
               this.condition.symbol="="
               this.lookup="fxactquery"
               this.prohibitframe('var')
              }else if(this.fields[key].type==='NUMBER'){
               this.opts = this.myoperators.NUMBER;
               this.showVar=false;
               this.showDate=false;
               this.showNum=true;
               this.condition.symbol="="
               this.prohibitframe('num')
              }else if (this.fields[key].type==='DATE') {
               this.opts = this.myoperators.DATE;
               this.showVar=false
               this.showNum=false
               this.showDate=true
               this.condition.symbol="="
               this.condition.dateformat='YYYY-MM'
               this.showdatechoice.showmonthframe0=false
               this.prohibitframe('date')
               }
           }
         }
      },

      //禁用方法
      prohibitframe(prohibit){
        console.log("所有的条件:"+JSON.stringify(this.conditions))
        for (const key in this.conditions) {
            if (this.conditions[key].field===this.condition.field) {
              switch (prohibit) {
                case 'date':
                  this.dateprohibit=true
                  break;
                case 'num':
                  this.numprohibit=true
                  break;
                case 'var':
                  this.varprohibit=true
                  break;
                default:
                  break;
              }
            }
         }
      },

      //NUMBER类型判断是否是 判空或区间
      changeNumSymbol(){
        if (this.condition.symbol==="bt") {
          this.shownuminput=false
          this.showsection=true
        }else if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.shownuminput=false
          this.showsection=false
        }else{
          this.showsection=false
          this.shownuminput=true
        }
      },

      //DATE类型判断是否是 判空或区间
      changeDateSymbol(){
        if (this.condition.symbol==="bt") {
          this.showdatechoice.showframe=true;
          this.hideDate()
          this.condition.dateformat='YYYY-MM'
          this.changeDateFormat()
        }else if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.showdatechoice.showframe=false;
          this.hideDate()
        }else{
          this.showdatechoice.showframe=true;
          this.hideDate()
          this.condition.dateformat='YYYY-MM'
          this.changeDateFormat()
        }
      },

      //VARCHAR类型判断是否是判空
      changeVarSymbol(){
        if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.showvarinput=false
        }else{
          this.showvarinput=true
        }
      },

      //DATE中 多选框 的改变事件 
      changeDateFormat(){
        if(this.condition.symbol == 'bt'){
          if (this.condition.dateformat=='YYYY-MM') {
            console.log("我是区间"+this.condition.dateformat)
            this.showdatechoice.showtimeframe1=false;
            this.showdatechoice.showdayframe1=false;
            this.showdatechoice.showmonthframe1=true;
          }else if(this.condition.dateformat=='YYYY-MM-DD'){
            console.log("我是区间"+this.condition.dateformat)
            this.showdatechoice.showmonthframe1=false;
            this.showdatechoice.showtimeframe1=false;
            this.showdatechoice.showdayframe1=true;
          }else if (this.condition.dateformat=='YYYY-MM-DD HH:24:MI:SS') {
            console.log("我是区间"+this.condition.dateformat)
            this.showdatechoice.showmonthframe1=false;
            this.showdatechoice.showdayframe1=false;
            this.showdatechoice.showtimeframe1=true;
          } 
        }else{
          if (this.condition.dateformat=='YYYY-MM') {
            console.log("我不是区间"+this.condition.dateformat)
            this.showdatechoice.showtimeframe0=false;
            this.showdatechoice.showdayframe0=false;
            this.showdatechoice.showmonthframe0=true;
          }else if(this.condition.dateformat=='YYYY-MM-DD'){
            console.log("我不是区间"+this.condition.dateformat)
            this.showdatechoice.showmonthframe0=false;
            this.showdatechoice.showtimeframe0=false;
            this.showdatechoice.showdayframe0=true;
          }else if (this.condition.dateformat=='YYYY-MM-DD HH:24:MI:SS') {
            console.log("我不是是区间"+this.condition.dateformat)
            this.showdatechoice.showmonthframe0=false;
            this.showdatechoice.showdayframe0=false;
            this.showdatechoice.showtimeframe0=true;
          }
        }
      },

      //DATE 隐藏 中所有的文本框
      hideDate(){
          this.showdatechoice.showmonthframe0=true
          this.showdatechoice.showdayframe0=false
          this.showdatechoice.showtimeframe0=false
          this.showdatechoice.showmonthframe1=false
          this.showdatechoice.showdayframe1=false
          this.showdatechoice.showtimeframe1=false
      },

      //添加条件初始化
      initAdd(){
            this.condition.type=''
            this.condition.textvalue=''
            this.checkedbtmin=''
            this.checkedbtmax=''
            this.condition.region=''
            this.condition.numvalue1=''
            this.condition.numvalue2=''
            this.shownuminput=true
            this.showvarinput=true
            this.showsection=false
            this.condition.datevalue1=''
            this.condition.datevalue2=[]
            this.condition.dateformat=''
            this.hideDate()
            this.changeField()
            if (this.conditions!=null) {
                console.log("初始化完成")
            }
      },
      
  
      //显示条件 区间和非区间
      showcondition(){
        var obj = {field:'',symbol:'',value:''}
        obj.field=this.condition.field
        if (this.condition.symbol==="bt") {
          obj.symbol=':'
          if(this.condition.type==="DATE"){
            obj.value=this.condition.datevalue1+"—"+this.condition.datevalue2 
          }else if(this.condition.type==="NUMBER"){
            obj.value=this.condition.numvalue1+"—"+this.condition.numvalue2 
          }
        }else{
          obj.symbol=this.condition.symbol
          if(this.condition.type==="DATE"){
            obj.value=this.condition.datevalue1
          }else if(this.condition.type==="NUMBER"){
            obj.value=this.condition.numvalue1
          }else if(this.condition.type==="VARCHAR2") {
            obj.value=this.condition.textvalue
          }
        }
        this.tags.push(obj)
        var tmp = JSON.stringify(this.condition);
        tmp = JSON.parse(tmp);
        this.conditions.push(tmp);
        this.$message({
          message: '添加条件成功!',
          type: 'success'
        });
      },

      //点击取消时的初始化
      initCondition(){
            this.condition.field=''
            this.condition.type=''
            this.condition.symbol=''
            this.condition.textvalue=''
            this.lookup=''
            this.checkedbtmin=''
            this.checkedbtmax=''
            this.condition.region=''
            this.condition.numvalue1=''
            this.condition.numvalue2=''
            this.shownuminput=true
            this.showvarinput=true
            this.showsection=false
            this.condition.datevalue1=''
            this.condition.datevalue2=[]
            this.condition.dateformat=''
            this.showVar=false
            this.showNum=false
            this.showDate=false
            this.conditions=[]
            this.tags=[];
            this.hideDate()

      },

      //条件框中的 确定点击事件
      news(){
        if (this.conditions.length!=0) {
           this.tagsNoe = this.tags;
           this.$emit('commitConditions',this.conditions)
           this.initCondition() 
           this.$message({
           message: '条件已成功提交!',
           type: 'success'
          });
        }else{
           this.$message.error('条件提交失败!');
        }
           this.visible = false;
      },

      //条件框中的取消事件
      cancel(){
        this.visible = false;
        this.initCondition()
      },
      
      //删除条件
      handleClose(tag){
        for (const key in this.conditions) {
          if(tag.field === this.conditions[key].field){
            this.tags.splice(this.tags.indexOf(tag),1);
            this.conditions.splice(key,1)
            this.changeField()
          }
        }
      }
   }
}
</script>

<style scoped>
/* 弹框的主体样式 */
#where{
  height: 250px;
  margin: auto;
  width: 500px;
}

/* 框的样式 */
.div{
  margin-bottom: 10px;
}
.div2{
  display: inline-block;
  width: 60px; 
  font-family:"微软雅黑";
}

/* 提示文本 区间 图标样式 */
.divicon{
  margin-top: 10px;
  margin-left: 70px;
}
/* VARCHAR中的 中区间的样式 */
.divnum{
  display: inline-block;
  width: 20px;
}

/* DATE中的多选样式 */
.dateradion{
  margin-top: 10px;
}

/* 标签 显示条件样式 */
#tag{
  margin-right: 10px; 
  margin-bottom: 10px;
}

/* 按钮的样式 */
.bon{
    margin-left: 10px;
}
</style>