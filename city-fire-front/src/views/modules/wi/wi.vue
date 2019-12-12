<template>
    <div class="mod-wi">
        <el-form :inline="true" >
           <el-form-item>
               <el-input v-model="dataForm.servicename" placeholder="服务接口名称" clearable></el-input>
           </el-form-item>
            <el-form-item>
            <el-button @click="getDataList()">查询</el-button>
            <el-button v-if="isAuth('wi:api:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
            <el-button v-if="isAuth('wi:api:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
            <el-button v-if="isAuth('wi:api:pause')" type="danger" @click="pauseHandle()" :disabled="dataListSelections.length <= 0">批量暂停</el-button>
            <el-button v-if="isAuth('wi:api:resume')" type="danger" @click="resumeHandle()" :disabled="dataListSelections.length <= 0">批量恢复</el-button>
        </el-form-item>
        </el-form>
        <el-table 
         :data="dataList"
         border
         v-loading="dataListLoading"
         @selection-change="selectionChangeHandle"
         style="width: 100%;">
         <el-table-column 
            type="selection"
            header-align="center"
            align="center"
            width="50">           
         </el-table-column>
         <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="80"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="servicename"
        header-align="center"
        align="center"
        label="接口名称">
      </el-table-column>
      <el-table-column
        prop = "url"
        header-align="center"
        align="center"
        label="接口访问地址">
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank" class="buttonText">{{scope.row.url}}</a>
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        label="请求类型">
      </el-table-column>
      <el-table-column
        prop="active"
        header-align="center"
        align="center"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.active === 1" size="small">正常</el-tag>
          <el-tag v-else size="small" type="danger">暂停</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="remark"
        header-align="center"
        align="center"
        label="备注">
      </el-table-column>      
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('wi:api:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('wi:api:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
          <el-button v-if="isAuth('wi:api:pause')" type="text" size="small" @click="pauseHandle(scope.row.id)">暂停</el-button>
          <el-button v-if="isAuth('wi:api:resume')" type="text" size="small" @click="resumeHandle(scope.row.id)">恢复</el-button>
        </template>
      </el-table-column>
      </el-table>
       <!-- 弹窗, 新增 / 修改 -->
     <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    </div>
</template>
<script>
    import AddOrUpdate from './wi-add-or-update'
    export default {
        data () {
            return {
                dataForm: {
                    servicename: ''
                },
                dataList: [],
                totalPage:'',
                dataListLoading: false,
                dataListSelections: [],
                addOrUpdateVisible: false
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
                url: this.$http.adornUrl('/wi/api/list'),
                method: 'get',
                params: this.$http.adornParams({
                  'page': this.pageIndex,
                  'limit': this.pageSize,
                  'servicename': this.dataForm.servicename
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
             // 每页数
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
            // 多选
            selectionChangeHandle (val) {
              this.dataListSelections = val
            },
            // 新增 / 修改
            addOrUpdateHandle (id) {
              this.addOrUpdateVisible = true
              this.$nextTick(() => {
                this.$refs.addOrUpdate.init(id)
              })
            },
            // 多选
            selectionChangeHandle (val) {
                this.dataListSelections = val
            },
            addOrUpdateHandle(id) {
              this.addOrUpdateVisible = true
              this.$nextTick(() => {
                this.$refs.addOrUpdate.init(id)
              })
            },
            // 删除
            deleteHandle (id) {
              var ids = id ? [id] : this.dataListSelections.map(item => {
                return item.id
              })
              this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.$http({
                  url: this.$http.adornUrl('/wi/api/delete'),
                  method: 'post',
                  data: this.$http.adornData(ids, false)
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
              }).catch(() => {})
            },
          // 暂停
          pauseHandle (id) {
            var ids = id ? [id] : this.dataListSelections.map(item => {
              return item.id
            })
            this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '暂停' : '批量暂停'}]操作?`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$http({
                url: this.$http.adornUrl('/wi/api/pause'),
                method: 'post',
                data: this.$http.adornData(ids, false)
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
            }).catch(() => {})
          },
          // 恢复
          resumeHandle (id) {
            var ids = id ? [id] : this.dataListSelections.map(item => {
              return item.jobId
            })
            this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '恢复' : '批量恢复'}]操作?`, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$http({
                url: this.$http.adornUrl('/wi/api/resume'),
                method: 'post',
                data: this.$http.adornData(ids, false)
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
            }).catch(() => {})
          }
        }
    }
</script>