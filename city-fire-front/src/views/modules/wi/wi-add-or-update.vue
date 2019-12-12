<template>
    <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
        <el-form :model="dataForm"  :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
            <el-form-item label="接口名称" prop="servicename">
                <el-input v-model="dataForm.servicename" placeholder="接口名称(首字母小写的类名称)"></el-input>
            </el-form-item>
            <el-form-item label="Java文件" prop="path">
                <el-button type="primary" @click="uploadHandle()">上传文件</el-button>
            </el-form-item>
            <el-form-item label="请求类型" prop="type">
                <el-select v-model="dataForm.type"
                   placeholder="请求类型" style="width:185px">
                <el-option
                    v-for="item in typeList"
                    :key="item"
                    :value="item">
                </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="状态" prop="active">
                <el-radio-group v-model="dataForm.active">
                    <el-radio  :label="1">活动</el-radio>
                    <el-radio  :label="0">禁用</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
                <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">取消</el-button>
            <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
        </span>
        <!-- 弹窗, 上传文件 -->
        <upload v-if="uploadVisible" v-on:uploadSucess="uploadSucess" ref="upload"></upload>
    </el-dialog>  
</template>
<script>
    import Upload from './wi-upload'
    export default {
       data () {
        return {
            visible: false,
            dataForm: {
                id: 0,
                servicename: '',
                path:'',
                url: '',
                type: '',   
                active:'',
                remark: ''
            },
            typeList: [
                'get',
                'post'
            ],
            uploadVisible: false,
            dataRule: {
                servicename: [
                    { required: true, message: '服务名不能为空', trigger: 'blur' }
                ],
                type:[
                    { required: true, message: '请选择请求类型', trigger: 'blur' }
                ],
                path:[
                    { required: true, message: '请上传文件', trigger: 'blur' }
                ]
            }
        }
       },
       components: {
         Upload
       },
       methods: {
           init (id) {
            this.dataForm.id = id || 0
            this.visible = true
            this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            this.dataForm.active = 1
            if (this.dataForm.id) {
                this.$http({
                url: this.$http.adornUrl(`/wi/api/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataForm.servicename = data.wi.servicename
                        this.dataForm.path = data.wi.path
                        this.dataForm.type = data.wi.type
                        this.dataForm.active = data.wi.active
                        this.dataForm.remark = data.wi.remark
                        console.log('active'+this.dataForm.active)                   
                    }
                })
            }
           })
          },
          dataFormSubmit() {
             this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                this.$http({
                    url: this.$http.adornUrl(`/wi/api/${!this.dataForm.id ? 'save' : 'update'}`),
                    method: 'post',
                    data: this.$http.adornData({
                        'id': this.dataForm.id || undefined,
                        'servicename': this.dataForm.servicename,
                        'path': this.dataForm.path,
                        'url':'',
                        'type': this.dataForm.type,
                        'active': this.dataForm.active,
                        'remark': this.dataForm.remark
                    })
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                            duration: 1500,
                            onClose: () => {
                                this.visible = false
                                this.$emit('refreshDataList')
                        }
                        })
                    } else {
                        this.$message.error(data.msg)
                    }
                })
            }
            })
          },
          uploadHandle () {
            this.uploadVisible = true
            this.$nextTick(() => {
                this.$refs.upload.init()
            })
          },
          uploadSucess (path) {
            this.dataForm.path = path
          }
     }     
    }
</script>