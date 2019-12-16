<template>
<!-- 条件弹框  -->
<el-dialog  title="条件添加" :close-on-click-modal="false" :visible.sync="visibleCondition">
    <!-- 操作框 -->
    <div id="main_condition"> 
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
          <el-select :disabled="prohibitDate" @change="changeDateSymbol()" style="width:400px" v-model="condition.symbol" filterable placeholder="请选择操作符"> 
            <el-option v-for="item in opts" :key="item.symbol" :label="item.comments" :value="item.symbol"></el-option>
          </el-select>
        </div>

        <div v-show="showDateChoice.showFrame" class="div">
            <div class="div2">
            格 式:
            </div>
             <el-radio-group  :disabled="prohibitDate" @change="changeDateFormat()" v-model="condition.dateFormat">
                <el-radio :value="dateFormatType[0].format" :label="dateFormatType[0].format">{{dateFormatType[0].comments}}</el-radio>
                <el-radio :value="dateFormatType[1].format" :label="dateFormatType[1].format">{{dateFormatType[1].comments}}</el-radio>
                <el-radio :value="dateFormatType[2].format" :label="dateFormatType[2].format">{{dateFormatType[2].comments}}</el-radio>
             </el-radio-group>
        </div>

        <div>
          <div v-show="showDateChoice.showMonthFrame" class="div" >
            <div class="div2">
                  月 份:
                  </div>
                  <el-date-picker
                        v-model="condition.dateValue1"
                        type="month"
                        placeholder="选择月份"
                        value-format="yyyy-MM"
                        :disabled="prohibitDate">
                  </el-date-picker>
            </div>

            
            <div v-show="showDateChoice.showDayFrame" class="div">
              <div class="div2">
                  日 期:
                  </div>
                  <el-date-picker
                      v-model="condition.dateValue1"
                      type="date"
                      placeholder="选择日期"
                      value-format="yyyy-MM-dd"
                      :disabled="prohibitDate">
                  </el-date-picker>
              </div>

            <div v-show="showDateChoice.showTimeFrame" class="div">
              <div class="div2">
                  时 间:
                  </div>
                  <el-date-picker 
                      v-model="condition.dateValue1"
                      type="datetime"
                      placeholder="选择时间"
                      value-format="yyyy-MM-dd hh:mm:ss"
                      default-time="12:00:00"
                      :disabled="prohibitDate">
                  </el-date-picker>
            </div>
        </div>

        <div v-show="showDateChoice.showMonthFrameSection" class="div">
            <div class="div2">
            月 份:
            </div>
              <el-checkbox v-model="checkedbtMin"></el-checkbox>
                <el-date-picker
                  v-model="condition.dateValue2"
                  type="monthrange"
                  range-separator="————"
                  start-placeholder="开始月份"
                  end-placeholder="结束月份"
                  value-format="yyyy-MM"
                  :disabled="prohibitDate">
                </el-date-picker>
              <el-checkbox v-model="checkedbtMax"></el-checkbox>
              <div class="divicon">
                <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
              </div>
          </div>
          
          <div v-show="showDateChoice.showDayFrameSection" class="div">
            <div class="div2">
            日 期:
            </div>
                <el-checkbox v-model="checkedbtMin"></el-checkbox>
                <el-date-picker
                  v-model="condition.dateValue2"
                  type="daterange"
                  range-separator="————"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd"
                  :disabled="prohibitDate">
                </el-date-picker>
                <el-checkbox v-model="checkedbtMax"></el-checkbox>
                <div class="divicon">
                  <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
                </div>
            </div>

            <div v-show="showDateChoice.showTimeFrameSection" class="div">
              <div class="div2">
                时 间:
                </div>
                <el-checkbox v-model="checkedbtMin"></el-checkbox>
                <el-date-picker
                  v-model="condition.dateValue2"
                  type="datetimerange"
                  range-separator="————"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  value-format="yyyy-MM-dd hh:mm:ss"
                  :disabled="prohibitDate">
                </el-date-picker>
                <el-checkbox v-model="checkedbtMax"></el-checkbox>
                <div class="divicon">
                  <font class="el-icon-info" style="color:#F56C6C"> 注:单选框选中则包含该值</font>
                </div>
            </div>
      </div>

      <!-- VARCHAR2 类型 -->
      <div v-show="showVar">
          <div v-show="showVariInput" class="div">
            <div class="div2">
            运 算 符:
            </div>
              <el-radio-group @change="changeVarSymbol()" v-model="condition.symbol">
                <el-radio :disabled="prohibitVar" v-for="item in opts" :key="item.symbol" :label="item.symbol" >{{item.comments}}</el-radio>
              </el-radio-group>
          </div>  
          <div v-show="showVariInput" class="div">
            <div class="div2">
            操 作 符:
            </div>
              <el-radio-group :disabled="prohibitVar"  v-model="lookup">
                <el-radio v-for="item in lookupDate" :key="item.mode" :label="item.mode" >{{item.comments}}</el-radio>
              </el-radio-group>
          </div>  
        <div v-show="showVariInput" class="div">
          <div class="div2">
          条件内容:
        </div>
            <el-input clearable :disabled="prohibitVar" style="width:400px" v-model="condition.textValue" placeholder="请输入条件内容为字符串类型"></el-input>
        </div>
      </div>
      
      <!-- NUMBER 类型 -->
      <div v-show="showNum">
        <div class="div">
          <div class="div2">
          运 算 符:
          </div>
          <el-select :disabled="prohibitNum" @change="changeNumSymbol()" style="width:400px" v-model="condition.symbol" filterable placeholder="请选择操作符"> 
            <el-option v-for="item in opts" :key="item.symbol" :label="item.comments" :value="item.symbol"></el-option>
          </el-select>
        </div>
        <div v-show="showNumInput" class="div">
            <div class="div2">
            条件内容:
            </div>
            <el-input :disabled="prohibitNum" clearable style="width:400px" v-model="condition.numValue1" placeholder="请输入条件内容为数字类型"></el-input>
        </div>
        <div v-show="showSection">
          <div class="div">
            <div class="div2">
            区间选择:
            </div>
            <el-checkbox :disabled="prohibitNum" v-model="checkedbtMin"></el-checkbox>
            <el-input :disabled="prohibitNum" style="width:170px" v-model="condition.numValue1" placeholder=""></el-input>
            <div class="divnum">
              <font class="el-icon-minus"></font>
            </div>
            <el-input :disabled="prohibitNum" style="width:170px" v-model="condition.numValue2" placeholder=""></el-input>
            <el-checkbox :disabled="prohibitNum" v-model="checkedbtMax"></el-checkbox>
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
      <el-button class="bon" @click="addACondition()" type="primary" plain>添加</el-button>
      <!-- 取消添加 按钮 -->
      <el-button @click="cancel()">取 消</el-button>
      <!-- 条件提交 按钮 -->
      <el-button type="primary" v-on:click="submission()">提交条件</el-button>
    </div>
  </el-dialog>
</template>
<script>
export default {
    data(){
      return{
          visibleCondition:false,
          fields:[],
          opts:[],
          tags:[],
          // DATE 隐藏显示 V-if
          showDateChoice:{
            showFrame:false,
            showMonthFrame:false,
            showDayFrame:false,
            showTimeFrame:false,
            showMonthFrameSection:false,
            showDayFrameSection:false,
            showTimeFrameSection:false
          },
          // DATE 多选框的值
          dateFormatType:[{format:'YYYY-MM',comments:'yyyy-MM'},
                         {format:'YYYY-MM-DD',comments:'yyyy-MM-dd'},
                         {format:'YYYY-MM-DD HH:24:MI:SS',comments:'yyyy-MM-dd hh:mm:ss'}
          ],
          //区间 选择按钮
          checkedbtMin:false,
          checkedbtMax:false,
          //禁用
          prohibitVar:false,
          prohibitNum:false,
          prohibitDate:false,
          //隐藏 显示 
          showVar:false,
          showNum:false,
          showDate:false,
          //NUMBER类型 中 文本和区间的 显示 隐藏 
          showNumInput:true,
          showSection:false,
          
          //VARVHAR类型 中文本显示
          showVariInput:true,
          //VARCHAR类型的单选框
          lookup:'',
          lookupDate:[{mode:'fxactquery',comments:'精确匹配'},
                      {mode:'fuzzyquery',comments:'模糊查询'}
          ],
          //传给后台的值
          condition:{            
            field:'',      //字段
            type:'',       //类型
            symbol:'',     //操作符
            textValue:'',  //文本值
            numValue1:'',  //数字值
            numValue2:'',  //数字值
            dateFormat:'', //返回时间类型 YYYY-MM-DD HH:24:MI:SS
            dateValue1:'', //时间值
            dateValue2:'', //时间值
          },
          conditions:[
          ],    
          //操作符    
          myoperators:{
            VARCHAR2:[
              {symbol:'=',comments:'等于'},
              {symbol:'<>',comments:'不等于'},
              {symbol:'IS NULL',comments:'为空'},
              {symbol:'IS NOT NULL',comments:'不为空'}
            ],
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
            this.condition.textValue=''

            this.condition.numValue1=''
            this.condition.numValue2=''
            this.showNumInput=true
            this.showVariInput=true
            this.showSection=false

            this.checkedbtMin=''
            this.checkedbtMax=''

            this.condition.dateValue1=''
            this.condition.dateValue2=''
            this.hideDate()
      },
      'visibleCondition':function(){
        if (this.visibleCondition===false) {
          this.initCondition()
        }
      }
    },
    methods: {
      //1.init初始化方法
      initShowCondition(fields) {
        this.visibleCondition=true
        this.fields = fields
        this.$nextTick(() => {})
      },

      //2.field下拉选的change事件 选中条件字段的类型判断 展示出不一样的运算符和条件内容框
      changeField(){
         this.prohibitVar=false;
         this.prohibitNum=false;
         this.prohibitDate=false;
         for (const key in this.fields) {
           if(this.fields[key].field===this.condition.field){
              if(this.fields[key].type==='VARCHAR2'){
               this.opts = this.myoperators.VARCHAR2;
               this.showDate=false;
               this.showNum=false;
               this.showVar=true;
               this.condition.symbol="="
               this.lookup="fxactquery"
               this.prohibitFrame('var')
              }else if(this.fields[key].type==='NUMBER'){
               this.opts = this.myoperators.NUMBER;
               this.showVar=false;
               this.showDate=false;
               this.showNum=true;
               this.condition.symbol="="
               this.prohibitFrame('num')
              }else if (this.fields[key].type==='DATE') {
               this.opts = this.myoperators.DATE;
               this.showVar=false
               this.showNum=false
               this.showDate=true
               this.condition.symbol="="
               this.condition.dateFormat='YYYY-MM'
               this.condition.showFrame=true
              //  this.showDateChoice.showMonthFrameSection=false
               this.prohibitFrame('date')
               }
            }
         }
      },

      //3.禁用方法
      prohibitFrame(prohibit){
        console.log("所有的条件:"+JSON.stringify(this.conditions))
        for (const key in this.conditions) {
            if (this.conditions[key].field===this.condition.field) {
              switch (prohibit) {
                case 'date':
                  this.prohibitDate=true
                  break;
                case 'num':
                  this.prohibitNum=true
                  break;
                case 'var':
                  this.prohibitVar=true
                  break;
                default:
                  break;
              }
            }
         }
      },

      //4.add添加条件事件
      addACondition(){
        //条件字段判空
        if (this.condition.field ===''){
          this.$message('请选择字段');
        //运算符判空
        }else if(this.condition.symbol ===''){
          this.$message('请选择操作符');
        }else{
          for (const key in this.fields) {
            if (this.fields[key].field===this.condition.field) {
               if(this.fields[key].type==='DATE'){
                //判断此字段是否已添加了
                if(this.prohibitDate===true){
                  this.$message.error('错误,此字段你已经添加');
                  return;
                }

                //判断运算符是否为IS NULL 或者 IS NOT NULL
                this.condition.type="DATE"
                if(this.condition.symbol==='IS NULL' | this.condition.symbol ==='IS NOT NULL'){
                  this.showCondition();
                  this.initAddMethod();
                  return
                }

                //判断是否是区间
                if (this.condition.symbol==="bt") {
                   //判断输入框是否为空
                  if(this.condition.dateValue2===''){
                    this.$message('条件内容不能为空');
                    return
                  }
                  if (this.checkedbtMin===false&this.checkedbtMax===false) {
                      this.condition.symbol="bt1"                       
                  }else if (this.checkedbtMin===false&this.checkedbtMax===true) {
                      this.condition.symbol="bt2"                       
                  }else if (this.checkedbtMin===true&this.checkedbtMax===false) {
                      this.condition.symbol="bt3"
                  }else if (this.checkedbtMin===true&this.checkedbtMax===true) {
                      this.condition.symbol="bt4"
                  }
                  this.condition.dateValue1=this.condition.dateValue2[0]
                  this.condition.dateValue2=this.condition.dateValue2[1]

                }
                
                if(this.condition.dateValue1===''){
                    this.$message('条件内容不能为空');
                    return
                }
                this.showCondition()
                // this.showDateChoice.showFrame=true
                // this.showDateChoice.showMonthFrame=true
               }else if (this.fields[key].type==='VARCHAR2') {
                  //判断此字段是否已添加了
                  if(this.prohibitVar===true){
                    this.$message.error('错误,此字段你已经添加')
                    return
                  }

                  //判断运算符是否为IS NULL 或者 IS NOT NULL
                  this.condition.type="VARCHAR2"
                  if(this.condition.symbol==='IS NULL' || this.condition.symbol ==='IS NOT NULL'){
                    this.condition.dateFormat=''
                    this.showCondition();
                    this.initAddMethod();
                    return
                  }

                  //判断是否选择了操作符
                  if(this.lookup===''){
                    this.$message('请选择操作符');
                    return
                  }

                  //判断输入框是否为空
                  if(this.condition.textValue===''){
                    this.$message('条件内容不能为空');
                    return
                  }

                  //判断是否为模糊查询
                  if (this.lookup==='fuzzyquery') {
                    if (this.condition.symbol==='=') {
                       this.condition.symbol='LIKE';
                    }else{
                       this.condition.symbol='NO LIKE';
                    }
                  }
                  this.condition.dateFormat=''
                  this.showCondition()
               }else if (this.fields[key].type==='NUMBER') {
                 //判断此字段是否已添加了
                 if(this.prohibitNum===true){
                    this.$message.error('错误,此字段你已经添加!');
                    return
                  }

                  //判断运算符是否为IS NULL 或者 IS NOT NULL
                  this.condition.type="NUMBER";
                  if(this.condition.symbol==='IS NULL' | this.condition.symbol ==='IS NOT NULL'){
                    this.condition.dateFormat=''
                    this.showCondition()
                    this.initAddMethod()
                    return
                  }
                  
                  //判断是否为空
                  if (this.condition.numValue1==='') {
                    this.$message('条件内容不能为空')
                    return
                  }

                  //区间判断值
                  if (this.condition.symbol==="bt") {
                    //区间判断是否为空
                    if(this.condition.numValue1==='' & this.condition.numValue2===''){
                        this.$message('条件内容不能为空')
                        return
                    }else if (this.numValue1>=this.numValue2) {
                        this.$message('最小值不能大于等于最大值')
                        return
                    } 
                    if (this.checkedbtMin===false&this.checkedbtMax===false) {
                        this.condition.symbol="bt1"                       
                      }else if (this.checkedbtMin===false&this.checkedbtMax===true) {
                          this.condition.symbol="bt2"                       
                      }else if (this.checkedbtMin===true&this.checkedbtMax===false) {
                          this.condition.symbol="bt3"
                      }else if (this.checkedbtMin===true&this.checkedbtMax===true) {
                          this.condition.symbol="bt4"
                      }
                  }
                  this.condition.dateFormat=''
                  this.showCondition()
               }         
            }
          }
          this.initAddMethod();
        }
      },
       
      //4.1条件添加方法 显示条件 区间和非区间
      showCondition(){
        var obj = {field:'',symbol:'',value:''}
        obj.field=this.condition.field
        if (this.condition.symbol==="bt") {
          obj.symbol=':'
          if(this.condition.type==="DATE"){
            obj.value=this.condition.dateValue1+"—"+this.condition.dateValue2 
          }else if(this.condition.type==="NUMBER"){
            obj.value=this.condition.numValue1+"—"+this.condition.numValue2 
          }
        }else{
          obj.symbol=this.condition.symbol
          if(this.condition.type==="DATE"){
            obj.value=this.condition.dateValue1
          }else if(this.condition.type==="NUMBER"){
            obj.value=this.condition.numValue1
          }else if(this.condition.type==="VARCHAR2") {
            obj.value=this.condition.textValue
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

      //4.2.NUMBER类型判断是否是 IS NULL IS NOT NULL或区间
      changeNumSymbol(){
        if (this.condition.symbol==="bt") {
          this.showNumInput=false
          this.showSection=true
        }else if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.showNumInput=false
          this.showSection=false
          this.condition.numValue1=''
          this.condition.numValue2=''
        }else{
          this.showSection=false
          this.showNumInput=true
        }
      },

      //4.3DATE类型判断是否是 IS NULL IS NOT NULL或区间
      changeDateSymbol(){
        if (this.condition.symbol==="bt") {
          this.hideDate()
          this.condition.dateFormat='YYYY-MM'
          this.changeDateFormat()
          this.showDateChoice.showMonthFrame=false
        }else if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.hideDate()
          this.showDateChoice.showFrame=false
          this.showDateChoice.showMonthFrame=false
          this.condition.dateValue1=''
          this.condition.dateValue2=''
        }else{
          this.hideDate()
          this.condition.dateFormat='YYYY-MM'
          this.changeDateFormat()
        }
      },

      //4.4VARCHAR类型判断是否是 IS NULL IS NOT NULL
      changeVarSymbol(){
        if(this.condition.symbol==="IS NULL" | this.condition.symbol==="IS NOT NULL"){
          this.showVariInput=false
          this.condition.textValue=''
        }else{
          this.showVariInput=true
        }
      },

      //4.DATE中 多选框 的改变事件 
      changeDateFormat(){
        this.condition.dateValue1=''
        this.condition.dateValue2=''
        if(this.condition.symbol == 'bt'){
          if (this.condition.dateFormat=='YYYY-MM') {
            console.log("我是区间"+this.condition.dateFormat)
            this.showDateChoice.showTimeFrameSection=false;
            this.showDateChoice.showDayFrameSection=false;
            this.showDateChoice.showMonthFrameSection=true;
          }else if(this.condition.dateFormat=='YYYY-MM-DD'){
            console.log("我是区间"+this.condition.dateFormat)
            this.showDateChoice.showMonthFrameSection=false;
            this.showDateChoice.showTimeFrameSection=false;
            this.showDateChoice.showDayFrameSection=true;
          }else if (this.condition.dateFormat=='YYYY-MM-DD HH:24:MI:SS') {
            console.log("我是区间"+this.condition.dateFormat)
            this.showDateChoice.showMonthFrameSection=false;
            this.showDateChoice.showDayFrameSection=false;
            this.showDateChoice.showTimeFrameSection=true;
          } 
        }else{
          if (this.condition.dateFormat=='YYYY-MM') {
            console.log("我不是区间"+this.condition.dateFormat)
            this.showDateChoice.showTimeFrame=false;
            this.showDateChoice.showDayFrame=false;
            this.showDateChoice.showMonthFrame=true;
          }else if(this.condition.dateFormat=='YYYY-MM-DD'){
            console.log("我不是区间"+this.condition.dateFormat)
            this.showDateChoice.showMonthFrame=false;
            this.showDateChoice.showTimeFrame=false;
            this.showDateChoice.showDayFrame=true;
          }else if (this.condition.dateFormat=='YYYY-MM-DD HH:24:MI:SS') {
            console.log("我不是是区间"+this.condition.dateFormat)
            this.showDateChoice.showMonthFrame=false;
            this.showDateChoice.showDayFrame=false;
            this.showDateChoice.showTimeFrame=true;
          }
        }
      },

      //DATE 隐藏文本框
      hideDate(){
          this.showDateChoice.showFrame=true
          this.showDateChoice.showMonthFrame=true
          this.showDateChoice.showDayFrame=false
          this.showDateChoice.showTimeFrame=false
          this.showDateChoice.showMonthFrameSection=false
          this.showDateChoice.showDayFrameSection=false
          this.showDateChoice.showTimeFrameSection=false
      },

      //添加条件初始化
      initAddMethod(){
            this.condition.numValue1=''
            this.condition.numValue2=''
            this.condition.type=''
            this.condition.textValue=''
            this.condition.dateValue1=''
            this.condition.dateValue2=''
            this.condition.dateFormat=''

            this.checkedbtMin=''
            this.checkedbtMax=''

            this.showNumInput=true
            this.showVariInput=true
            this.showSection=false

            this.hideDate()
            this.changeField()
            if (this.conditions!=null) {
                console.log("初始化完成")
            }
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
      },

      //点击取消时的初始化
      initCondition(){
            this.condition.field=''
            this.condition.type=''
            this.condition.symbol=''
            this.condition.dateValue1=''
            this.condition.dateValue2=''
            this.condition.dateFormat=''
            this.condition.numValue1=''
            this.condition.numValue2=''

            this.lookup=''
            this.checkedbtMin=''
            this.checkedbtMax=''

            this.showNumInput=true
            this.showVariInput=true
            this.showSection=false

            this.showVar=false
            this.showNum=false
            this.showDate=false

            this.conditions=[]
            this.tags=[];
            this.hideDate()
      },

      //条件框中的 提交条件点击事件
      submission(){
        if (this.conditions.length!=0) {
           this.$emit('commitConditions',this.conditions,this.tags)
           this.initCondition() 
           this.$message({
           message: '条件已成功提交!',
           type: 'success'
           });
        }else{
           this.$message.error('条件提交失败!');
        }
           this.visibleCondition = false;
      },

      //条件框中的取消事件
      cancel(){
        this.visibleCondition = false;
        this.initCondition()
      },
   }
}
</script>

<style scoped>
/* 弹框的主体样式 */
#main_condition{
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