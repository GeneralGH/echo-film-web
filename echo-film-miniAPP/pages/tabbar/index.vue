<template>
	<view>
		<HomeHeader @typeChange="typeChange" />
	</view>
</template>

<script>
	import HomeHeader from '@/components/homeHeader.vue';
	import { getFilmList } from '@/common/js/film.js'
	
	export default {
		components: { HomeHeader },
		data() {
			return {
				listQuery: {
					pageNum: 1,
					pageSize: 10,
					name: '',
					filmType: ''
				},
				list: []
			};
		},
		
		methods: {
			typeChange(e) {
				this.listQuery.filmType = e
				this.listQuery.pageNum = 1
				this.list = []
				this.getList()
			},
			
			getList() {
				getFilmList(this.listQuery)
				.then(res => {
					
				})
			}
		},
		
		onLoad() {
			this.getList()
		}
	}
</script>

<style lang="scss">

</style>
