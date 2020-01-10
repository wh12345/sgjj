<template>
    <div class="mod-tq">
        <el-form :inline="true" >
           <el-form-item>
             <el-select v-model="searchkey" filterable placeholder="请选择搜索条件">
              <el-option
                v-for="item in searchkeys"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
              </el-select>
           </el-form-item>
           <el-form-item>            
               <el-input v-model="dataForm.content" placeholder="请输入搜索内容" clearable></el-input>
           </el-form-item>
            <el-form-item>
            <el-button @click="getDataList()">查询</el-button>
            <el-button v-if="isAuth('tq:supervise:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        </el-form-item>
        </el-form>
        <el-table 
         :data="dataList"
         border
         v-loading="dataListLoading"
         style="width: 100%;">
         <el-table-column
          prop="cavalryId"
          header-align="center"
          align="center"
          width="80"
          label="序号">
        </el-table-column>
        <el-table-column
          prop="dwmc"
          header-align="center"
          align="center"
          label="单位名称">
        </el-table-column>
        <el-table-column
          prop = "jybh"
          header-align="center"
          align="center"
          label="警员编号">
        </el-table-column>
        <el-table-column
          prop="jyxm"
          header-align="center"
          align="center"
          label="警员姓名">
        </el-table-column>
        <el-table-column
          prop="jyzw"
          header-align="center"
          align="center"
          label="警员职位">
        </el-table-column>
        <el-table-column
          prop="hphm"
          header-align="center"
          align="center"
          label="号牌号码">
        </el-table-column>   
        <el-table-column
          prop="phonenumber"
          header-align="center"
          align="center"
          label="手机号码">
        </el-table-column>     
        <el-table-column
          fixed="right"
          header-align="center"
          align="center"
          width="150"
          label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('tq:supervise:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.cavalryId)">修改</el-button>
          <el-button v-if="isAuth('tq:supervise:delete')" type="text" size="small" @click="deleteHandle(scope.row.cavalryId)">删除</el-button>
          <el-button v-if="true" type="text" size="small" @click="check(scope.row.cavalryId)">考核</el-button>
        </template>
      </el-table-column>
      </el-table>  
      <el-pagination
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle"
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalPage"
        layout="total, sizes, prev, pager, next, jumper">
      </el-pagination> 
      <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate"  @refreshDataList="getDataList"></add-or-update>
    </div>
</template>
<script>
    import AddOrUpdate  from './tq-add-or-update'
    export default {
        data () {
            return {
                dataForm: {
                    content: ''
                },
                addOrUpdateVisible: false,
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                dataListSelections: [],
                searchkeys: [{
                    value: 'dwmc',
                    label: '单位名称'
                  }, {
                    value: 'jybh',
                    label: '警员编号'
                  }, {
                    value: 'jyxm',
                    label: '警员姓名'
                  }, {
                    value: 'hphm',
                    label: '铁骑车牌'
                  }],
                  searchkey:''
            }
        },
        components: {
          AddOrUpdate
        },
        activated () {
          this.getDataList()
        },
        methods:  {
            getDataList() {
              this.dataListLoading = true
              this.$http({
                url: this.$http.adornUrl('/tq/supervise/list'),
                method: 'get',
                params: this.$http.adornParams({
                  'page': this.pageIndex,
                  'limit': this.pageSize,
                  'searchkey': this.searchkey,
                  'content': this.dataForm.content
                })
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataList = data.page.list
                  this.totalPage = data.page.totalCount
                } else {
                  this.dataList = []
                  this.totalPage = 0
                }
                this.dataListLoading = false
              })
            },
             //每页数
            sizeChangeHandle (val) {
              this.pageSize = val
              this.pageIndex = 1
              this.getDataList()
            },
            // 当前页
            currentChangeHandle (val) {
              this.pageIndex = val
              this.getDataList()
            },
    
            // 新增 / 修改
            addOrUpdateHandle (id) {
              this.addOrUpdateVisible = true
              this.$nextTick(() => {
                this.$refs.addOrUpdate.init(id)
              })
            },
            // 删除
            deleteHandle (id) {            
                this.$http({
                  url: this.$http.adornUrl('/tq/supervise/delete/'+id),
                  method: 'get',
                  params: this.$http.adornParams()
                }).then(({data}) => {
                  if (data && data.code === 0) {
                    this.$message({
                      message: '操作成功',
                      type: 'success',
                      duration: 1500,
                      onClose: () => {
                        this.getDataList()
                      }
                    })
                  } else {
                    this.$message.error(data.msg)
                  }
                })
            },
            check(id) {
              this.$notify.info({
                title: '消息',
                message: '攻城狮正在开发中'
              })
            }          
        }
    }
</script>