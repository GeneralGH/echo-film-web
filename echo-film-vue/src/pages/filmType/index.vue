<!--  -->
<template>
  <div>
    <div>
    <t-card :bordered="false" title="用户管理">
      <t-row justify="space-between">
        <t-col>
        </t-col>
        <t-col>
          <t-button theme="warning" @click="addOrUpdate()">新增</t-button>
        </t-col>
      </t-row>

      <div style="margin-top: 50px;">
        <t-table :pagination="null" bordered hover stripe tableLayout="auto" row-key="index" :data="data"
          :columns="columns" style="margin-bottom: 50px;">
          <template #actions="{ row }">
            <t-space>
              <t-button @click="addOrUpdate(row)" size="small">编辑</t-button>
              <t-popconfirm theme="danger" :visible="row.delShow" content="确认删除吗" @cancel="row.delShow = false" @confirm="delConfirm(row)">
                <t-button theme="danger" size="small" @click="row.delShow = true">删除</t-button>
              </t-popconfirm>
            </t-space>
          </template>
        </t-table>
      </div>

      <AddOrUpdate ref="addOrUpdate" :actionType="actionType" :editObj="editObj" @reList="getList" />
    </t-card>
  </div>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import 《组件名称》 from '《组件路径》';
import FilmTypeApi from '@/api/filmType'
import AddOrUpdate from './addOrUpdate.vue'

export default {
  // import引入的组件需要注入到对象中才能使用
  components: { AddOrUpdate },
  name: 'filmType',
  data() {
    // 这里存放数据
    return {
      data: [],
      totalCount: 0,
      columns: [
        { colKey: 'name', title: '类型', align: 'center' },
        { cell: 'actions', title: '操作', align: 'center' }
      ],
      editObj: {},
      actionType: 1
    };
  },
  // 监听属性 类似于data概念
  computed: {},
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    getList() {
      this.$request.get(FilmTypeApi.getFilmTypeListUrl)
      .then(res => {
        this.data = res.data.data.map((item) => { return { ...item, delShow: false } })
      })
    },

    addOrUpdate(row) {
      if (row) {
        this.editObj = row;
        this.actionType = 2
      } else {
        this.actionType = 1
      }
      this.$refs.addOrUpdate.show = true
    },

    delConfirm(row) {
      this.$request.delete(FilmTypeApi.delFilmTypeUrl + row.id)
      .then(res => {
        this.$message.success('删除成功')
        row.delShow = false
        this.getList()
      })
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getList()
  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {
  },
  beforeCreate() { }, // 生命周期 - 创建之前
  beforeMount() { }, // 生命周期 - 挂载之前
  beforeUpdate() { }, // 生命周期 - 更新之前
  updated() { }, // 生命周期 - 更新之后
  beforeDestroy() { }, // 生命周期 - 销毁之前
  destroyed() { }, // 生命周期 - 销毁完成
  activated() { }
}
</script>

<style scoped lang="less"></style>