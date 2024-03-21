<!--  -->
<template>
  <div>
    <t-card :bordered="false">
      <t-row justify="space-between">
        <t-col>
          <t-space>
            <t-form layout="inline" colon labelAlign="right" :labelWidth="80">
              <t-form-item label="名称">
                <t-input placeholder="请输入内容" v-model="listQuery.name" clearable style="width: 240px;" />
              </t-form-item>
              <t-form-item label="类型">
                <t-select clearable showArrow multiple v-model="typeIds" placeholder="请输入影片类型">
                  <t-option v-for="item in filmTypeList" :key="item.id.toString()" :label="item.name"
                    :value="item.id.toString()" />
                </t-select>
              </t-form-item>
              <t-form-item label="是否推荐">
                <t-select clearable showArrow v-model="listQuery.isRecommend" placeholder="请选择是否推荐">
                  <t-option key="1" label="是" value="1" />
                  <t-option key="2" label="否" value="2" />
                </t-select>
              </t-form-item>
            </t-form>

            <t-button @click="getList">查询</t-button>
          </t-space>
        </t-col>
        <t-col>
          <t-button theme="warning" @click="addOrUpdate()">新增</t-button>
        </t-col>
      </t-row>

      <div style="margin-top: 50px;">
        <t-table :pagination="null" bordered hover stripe tableLayout="auto" row-key="index" :data="data"
          :columns="columns" style="margin-bottom: 50px;">
          <template #typeIds="{ row }">
            {{ typeShow(row.typeIds) }}
          </template>
          <template #watchNumber="{ row }">
            {{ row.watchNumber ? row.watchNumber : 0 }}
          </template>
          <template #cover="{ row }">
            <t-image-viewer v-model="row.previewShow" :images="[JSON.parse(row.cover).url]" :closeOnEscKeydown="false">
              <template #trigger="{ open }">
                <div class="tdesign-demo-image-viewer__ui-image" @click="open">
                  <img alt="test" :src="JSON.parse(row.cover).url" class="tdesign-demo-image-viewer__ui-image--img" />
                  <div class="tdesign-demo-image-viewer__ui-image--hover">
                    <span><browse-icon size="1.4em" /> 预览</span>
                  </div>
                </div>
              </template>
            </t-image-viewer>
            <!-- <div style="overflow: hidden; width: 150px; height: 200px; margin: 0 auto;">
              <img :src="JSON.parse(row.cover).url" style="width: 150px;" />
            </div> -->
          </template>
          <template #releaseTime="{ row }">
            {{ row.releaseTime.substring(0, 10) }}
          </template>
          <template #actions="{ row }">
            <t-space>
              <t-button @click="addOrUpdate(row)" size="small">编辑</t-button>
              <t-popconfirm theme="danger" :visible="row.delShow" content="确认删除吗" @cancel="row.delShow = false"
                @confirm="delConfirm(row)">
                <t-button theme="danger" size="small" @click="row.delShow = true">删除</t-button>
              </t-popconfirm>
            </t-space>
          </template>
        </t-table>
        <t-pagination :total="totalCount" :current="listQuery.pageNum" :pageSize="listQuery.pageSize" showJumper
          showPageNumber showPageSize showPreviousAndNextBtn totalContent @change="pageChange" />
      </div>

      <AddOrUpdate ref="addOrUpdate" :actionType="actionType" :editObj="editObj" :filmTypeList="filmTypeList"
        @reList="getList" />
    </t-card>
  </div>
</template>

<script>
// 这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
// 例如：import 《组件名称》 from '《组件路径》';
import filmApi from '@/api/film'
import filmTypeApi from '@/api/filmType';
import AddOrUpdate from './addOrUpdate.vue'

export default {
  // import引入的组件需要注入到对象中才能使用
  components: { AddOrUpdate },
  data() {
    // 这里存放数据
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        isRecommend: ''
      },
      data: [],
      totalCount: 0,
      columns: [
        { colKey: 'name', title: '影片名称', align: 'center' },
        { cell: 'typeIds', title: '类型', align: 'center' },
        { cell: 'watchNumber', title: '播放量', align: 'center' },
        { cell: 'cover', title: '封面', align: 'center' },
        { colKey: 'director', title: '导演', align: 'center' },
        { colKey: 'actor', title: '演员', align: 'center' },
        { cell: 'releaseTime', title: '上映日期', align: 'center' },
        { cell: 'actions', title: '操作', align: 'center' }
      ],
      actionType: 1,   // 1 新增，2 编辑
      editObj: {},
      filmTypeList: [],
      typeIds: []
    };
  },
  // 监听属性 类似于data概念
  computed: {},
  // 监控data中的数据变化
  watch: {
    'typeIds'(val) {
      this.listQuery.filmType = val.length ? val.join(',') : ''
    }
  },
  filters: {
  },
  // 方法集合
  methods: {
    getList() {
      this.$request.post(filmApi.getFilmListUrl, this.listQuery)
        .then(res => {
          this.data = res.data.data.list.map((item) => { return { ...item, delShow: false, previewShow: false } })
          this.totalCount = res.data.data.total
        })
    },

    pageChange(e) {
      this.listQuery.pageNum = e.current
      this.listQuery.pageSize = e.pageSize
      this.getList()
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
      this.$request.delete(filmApi.delFilmUrl + row.filmId)
        .then(res => {
          this.$message.success('删除成功')
          row.delShow = false
          this.getList()
        })
    },

    getFilmTypes() {
      this.$request.get(filmTypeApi.getFilmTypeListUrl)
        .then(res => {
          this.filmTypeList = res.data.data
        })
    },

    typeShow(typeIds) {
      let that = this
      let typeList = typeIds.split(',')
      const typeStr = typeList.map(item => {
        const foundObj = that.filmTypeList.find(obj => obj.id === parseInt(item));
        return foundObj ? foundObj.name : "";
      }).join("/")
      return typeStr
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getList()
    this.getFilmTypes()
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
  activated() { },
}
</script>

<style scoped lang="less">
.tdesign-demo-image-viewer__ui-image {
  width: 160px;
  height: 160px;
  display: inline-flex;
  position: relative;
  justify-content: center;
  align-items: center;
  border-radius: var(--td-radius-small);
  overflow: hidden;
}

.tdesign-demo-image-viewer__ui-image:hover .tdesign-demo-image-viewer__ui-image--hover {
  opacity: 1;
  cursor: pointer;
}

.tdesign-demo-image-viewer__ui-image--img {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
  cursor: pointer;
  position: absolute;
}
</style>