<!--  -->
<template>
    <t-dialog closeOnEscKeydown destroyOnClose :footer="false" header :visible.sync="show" @close="close" :width="850">
        <t-form :data="formData" :rules="rules" ref="form" @submit="onSubmit" colon>
            <t-form-item label="类型名称" name="name">
                <t-input v-model="formData.name" placeholder="请输入类型名称"></t-input>
            </t-form-item>

            <t-form-item style="margin-left: 100px">
                <t-space size="50px">
                    <t-button theme="primary" type="submit">提交</t-button>
                    <t-button theme="default" variant="base" @click="close">取消</t-button>
                </t-space>
            </t-form-item>
        </t-form>
    </t-dialog>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import FilmTypeApi from '@/api/filmType'

export default {
    name: 'AddOrUpdate',
    //import引入的组件需要注入到对象中才能使用
    components: {},
    data() {
        //这里存放数据
        return {
            show: false,
            formData: {
                name: ''
            },
            rules: {
                name: [
                    { required: true, message: '类型名称必填', type: 'error', trigger: 'blur', }
                ]
            }
        };
    },
    props: {
        actionType: { type: Number, default: 1 },
        editObj: { type: Object, default: () => { } }
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {
        'show' (val) {
            if (val) {
                if (this.actionType == 2) {
                    this.formData = JSON.parse(JSON.stringify(this.editObj))
                }
            }
        }
    },
    //方法集合
    methods: {
        close() {
            this.show = false
            this.formData = {
                name: ''
            }
        },

        onSubmit({ validateResult, firstError }) {
            if (validateResult === true) {
                if (this.actionType == 1) {
                    this.$request.post(FilmTypeApi.addFilmTypeUrl, this.formData)
                        .then(res => {
                        if (res.status != 200) {
                            this.$message.error(res.msg)
                            return
                        }
                            this.$message.success('提交成功');
                            this.close()
                            this.$emit('reList')
                        })
                } else {
                    this.$request.put(FilmTypeApi.updateFilmTypeUrl, this.formData)
                    .then(res => {
                        if (res.status != 200) {
                            this.$message.error(res.msg)
                            return
                        }
                        this.$message.success('提交成功');
                        this.close()
                        this.$emit('reList')
                    })
                }
            } else {
                this.$message.warning(firstError);
            }
        }
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {

    },
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {

    },
    beforeCreate() { }, //生命周期 - 创建之前
    beforeMount() { }, //生命周期 - 挂载之前
    beforeUpdate() { }, //生命周期 - 更新之前
    updated() { }, //生命周期 - 更新之后
    beforeDestroy() { }, //生命周期 - 销毁之前
    destroyed() { }, //生命周期 - 销毁完成
    activated() { }, //如果页面有keep-alive缓存功能，这个函数会触发
}
</script>

<style scoped></style>