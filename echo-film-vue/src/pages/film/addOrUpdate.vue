<!--  -->
<template>
    <t-dialog closeOnEscKeydown destroyOnClose :footer="false" header :visible.sync="show" @close="close" :width="850">
        <t-form :data="formData" :rules="rules" ref="form" @submit="onSubmit" colon>
            <t-form-item label="影片名称" name="name">
                <t-input v-model="formData.name" placeholder="请输入影片名称" clearable></t-input>
            </t-form-item>
            <t-form-item label="影片类型" name="typeIds">
                <t-select clearable showArrow multiple v-model="typeIds" placeholder="请输入影片类型">
                    <t-option v-for="item in filmTypeList" :key="item.id.toString()" :label="item.name" :value="item.id.toString()" />
                </t-select>
            </t-form-item>
            <t-form-item label="影片海报" name="cover">
                <t-upload accept="image/*" theme="image" v-model="coverList" :requestMethod="uploadCover" />
            </t-form-item>
            <t-form-item label="导演" name="director">
                <t-input v-model="formData.director" placeholder="请输入导演" clearable></t-input>
            </t-form-item>
            <t-form-item label="演员" name="actor">
                <t-input v-model="formData.actor" placeholder="请输入演员" clearable></t-input>
            </t-form-item>
            <t-form-item label="影片资源" name="filmUrl">
                <t-upload accept="video/*" v-model="filmList" :requestMethod="uploadFilm" />
            </t-form-item>
            <t-form-item label="是否推荐" name="isRecommend">
                <t-radio-group v-model="formData.isRecommend">
                    <t-radio :value="1">是</t-radio>
                    <t-radio :value="2">否</t-radio>
                </t-radio-group>
            </t-form-item>
            <t-form-item label="播放量">
                {{ formData.watchNumber ? formData.watchNumber : 0 }}
            </t-form-item>
            <t-form-item label="上映时间" name="releaseTime">
                <t-date-picker clearable v-model="formData.releaseTime" placeholder="请输入上映时间" />
            </t-form-item>
            <t-form-item label="简介" name="info">
                <div style="border: 1px solid #ccc;">
                    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editor" :defaultConfig="toolbarConfig"
                        mode="default" />
                    <Editor style="height: 500px; overflow-y: hidden;" v-model="formData.info"
                        :defaultConfig="editorConfig" mode="default" @onCreated="onCreated" />
                </div>
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
import filmApi from '@/api/film'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { uploadFilesToServer } from '@/utils/uploadFilesToServer'

export default {
    name: 'AddOrUpdate',
    //import引入的组件需要注入到对象中才能使用
    components: { Editor, Toolbar },
    data() {
        //这里存放数据
        return {
            show: false,
            coverList: [],
            filmList: [],
            typeIds: [],
            formData: {
                cover: '',
                name: '',
                typeIds: '',
                info: '',
                director: '',
                actor: '',
                filmUrl: '',
                isRecommend: 1,
                releaseTime: ''
            },
            rules: {
                name: [
                    { required: true, message: '影片名称必填', type: 'error', trigger: 'blur', }
                ],
                director: [
                    { required: true, message: '影片导演必填', type: 'error', trigger: 'blur', }
                ],
                actor: [
                    { required: true, message: '影片演员必填', type: 'error', trigger: 'blur', }
                ],
                releaseTime: [
                    { required: true, message: '影片上映时间必填', type: 'error', trigger: 'blur', }
                ]
            },
            editor: null,
            html: '',
            toolbarConfig: {
                excludeKeys: [
                    'fullScreen',
                    'codeBlock',
                    'todo',
                    'emotion'
                ]
            },
            editorConfig: {
                placeholder: '请输入内容...',
                MENU_CONF: {
                    uploadImage: {
                        customUpload: this.uploadImg
                    },
                    uploadVideo: {
                        customUpload: this.uploadVideos
                    }
                }
            }
        };
    },
    props: {
        actionType: { type: Number, default: 1 },
        editObj: { type: Object, default: () => { } },
        filmTypeList: { type: Array, default: () => [] }
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {
        'show'(val) {
            if (val) {
                if (this.actionType == 2) {
                    this.formData = JSON.parse(JSON.stringify(this.editObj))
                    this.typeIds = this.formData.typeIds.split(',')
                    this.coverList.push(JSON.parse(this.formData.cover))
                    this.filmList.push(JSON.parse(this.formData.filmUrl))
                }
            }
        }
    },
    //方法集合
    methods: {
        onCreated(editor) {
            this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
        },

        close() {
            this.show = false
            this.formData = {
                cover: '',
                name: '',
                typeIds: '',
                info: '',
                director: '',
                actor: '',
                filmUrl: '',
                isRecommend: 1,
                releaseTime: ''
            }
            this.coverList = []
            this.filmList = []
            this.typeIds = []
        },

        onSubmit({ validateResult, firstError }) {
            this.formData.cover = this.coverList.length ? JSON.stringify(this.coverList[0]) : ''
            this.formData.filmUrl = this.filmList.length ? JSON.stringify(this.filmList[0]) : ''
            this.formData.typeIds = this.typeIds.length ? this.typeIds.join(',') : ''
            if (!this.formData.cover) {
                this.$message.error('请上传封面')
                return
            }
            if (!this.formData.filmUrl) {
                this.$message.error('请上传影片')
                return
            }
            if (!this.formData.info) {
                this.$message.error('请填写影片简介')
                return
            }
            if (!this.formData.typeIds) {
                this.$message.error('请选择影片类型')
                return
            }
            if (validateResult === true) {
                if (this.actionType == 1) {
                    this.$request.post(filmApi.addFilmUrl, this.formData)
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
                    this.$request.put(filmApi.updateFilmUrl, this.formData)
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
        },

        async uploadImg(file, insertFn) {
            const res = await uploadFilesToServer('file', file)
            insertFn(res.response.data.url, res.response.data.fileName)
        },

        async uploadVideos(file, insertFn) {
            const res = await uploadFilesToServer('file', file)
            insertFn(res.response.data.url, res.response.data.fileName)
        },

        async uploadCover(file) {
            file.raw.name = encodeURI(file.raw.name)
            const res = await uploadFilesToServer('file', file.raw)
            this.coverList.push({ name: file.name, url: res.response.data.url })
        },

        async uploadFilm(file) {
            console.log(file.raw.name)
            file.raw.name = encodeURI(file.raw.name)
            const res = await uploadFilesToServer('file', file.raw)
            this.filmList.push({ name: file.name, url: res.response.data.url })
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
    beforeDestroy() {
        const editor = this.editor
        if (editor == null) return
        editor.destroy() // 组件销毁时，及时销毁编辑器
    }, //生命周期 - 销毁之前
    destroyed() { }, //生命周期 - 销毁完成
    activated() { }, //如果页面有keep-alive缓存功能，这个函数会触发
}
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>
<style scoped></style>