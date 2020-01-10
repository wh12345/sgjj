<template>
    <el-dialog
    :title="!dataForm.id ? '新增铁骑' : '修改铁骑'"
    :close-on-click-modal="false"
    :visible.sync="visible">
        <el-form :model="dataForm"  :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
            <el-form-item label="警员姓名" prop="jyxm">
                <el-input v-model="dataForm.jyxm" placeholder="警员姓名"></el-input>
            </el-form-item>
            <el-form-item label="警员编号" prop="jybh">
                <el-input v-model="dataForm.jybh" placeholder="警员编号"></el-input>
            </el-form-item>
            <el-form-item label="单位名称" prop="dwbh">
                <el-select v-model="dataForm.dwbh"
                   placeholder="单位名称" style="width:185px">
                <el-option
                    v-for="item in dws"
                    :key="item.DWBH"
                    :label="item.DWMC"
                    :value="item.DWBH">
                </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="警员职位" prop="jyzw">
                 <el-input v-model="dataForm.jyzw" placeholder="警员职位"></el-input>
            </el-form-item>
            <el-form-item label="车牌号码" prop="hphm">
                 <el-input v-model="dataForm.hphm" placeholder="车牌号码"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phonenumber">
                <el-input v-model="dataForm.phonenumber" placeholder="手机号码"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
                <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="visible = false">取消</el-button>
            <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
        </span>
    </el-dialog>  
</template>
<script>
    import { isEmail, isMobile } from '@/utils/validate'
    export default {
       data () {
        var validateMobile = (rule, value, callback) => {
            if (!isMobile(value)) {
                callback(new Error('手机号格式错误'))
            } else {
                callback()
            }
        }
        return {
            visible: false,
            dataForm: {
                cavalryId: 0,
                dwbh:'',
                dwmc: '',
                jybh:'',
                jyxm: '',
                jyzw: '',   
                hphm:'',
                phonenumber:'',
                remark: ''
            },
            dws: [ ],
            uploadVisible: false,
            dataRule: {
                jyxm: [
                    { required: true, message: '姓名不能为空', trigger: 'blur' }
                ],
                jybh:[
                    { required: true, message: '警号不能为空', trigger: 'blur' }
                ],
                dwbh:[
                    {required:true, message:'请选择单位',trigger: 'blur'}
                ],
                hphm:[
                    { required: true, message: '车牌号码不能为空', trigger: 'blur' }
                ],
                phonenumber:[
                    { required: true, message: '手机号码不能为空', trigger: 'blur' },
                    { validator: validateMobile, trigger: 'blur' }
                ]
            }
        }
       },
       methods: {
           init (id) {
            this.dataForm.cavalryId = id || 0
            this.getDws()
            this.visible = true
            this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()           
            this.dataForm.active = 1
            if (this.dataForm.cavalryId) {
                this.$http({
                url: this.$http.adornUrl(`/tq/supervise/info/${this.dataForm.cavalryId}`),
                method: 'get',
                params: this.$http.adornParams()
                }).then(({data}) => {
                    if (data && data.code === 0) {
                        this.dataForm.dwbh = data.cavalry.dwbh
                        this.dataForm.dwmc = data.cavalry.dwmc
                        this.dataForm.jybh = data.cavalry.jybh
                        this.dataForm.jyxm = data.cavalry.jyxm
                        this.dataForm.jyzw = data.cavalry.jyzw
                        this.dataForm.hphm = data.cavalry.hphm  
                        this.dataForm.phonenumber = data.cavalry.phonenumber
                        this.dataForm.remark = data.cavalry.remark             
                    }
                })
            }
           })
          },
          dataFormSubmit() {
             this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                this.$http({
                    url: this.$http.adornUrl(`/tq/supervise/${!this.dataForm.cavalryId ? 'save' : 'update'}`),
                    method: 'post',
                    data: this.$http.adornData({
                        'cavalryId': this.dataForm.cavalryId || undefined,
                        'dwmc': this.dataForm.dwmc,
                        'dwbh': this.dataForm.dwbh,
                        'jybh': this.dataForm.jybh,
                        'jyxm': this.dataForm.jyxm,
                        'jyzw': this.dataForm.jyzw,
                        'hphm': this.dataForm.hphm,
                        'phonenumber': this.dataForm.phonenumber,
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
          getDws() {
              this.$http({
                    url: this.$http.adornUrl(`/tq/supervise/listdw`),
                    method: 'get',
                    params: this.$http.adornParams()
                }).then(({data}) => {
                    if (data && data.code === 0) {                      
                        this.dws = data.dws
                        console.log(data.dws)
                    }
                })
          }
     }     
    }
</script>