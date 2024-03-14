import Layout from '@/layouts/index.vue';

export default [
  {
    path: '/',
    component: Layout,
    redirect: '/user/manage',
    name: 'userManage',
    meta: {
      title: '用户管理',
      single: true
    },
    children: [
      {
        path: '/user/manage',
        name: 'manage',
        component: () => import('@/pages/user/index.vue'),
        meta: { title: '用户管理' },
      }
    ],
  },
  {
    path: '/filmIndex',
    component: Layout,
    redirect: '/film',
    name: 'filmIndex',
    meta: {
      title: '影视'
    },
    children: [
      {
        path: 'film',
        name: 'film',
        component: () => import('@/pages/film/index.vue'),
        meta: { title: '影视列表' },
      },
      {
        path: 'filmType',
        name: 'filmType',
        component: () => import('@/pages/filmType/index.vue'),
        meta: { title: '影视类型' },
      }
    ],
  }
  /* {
    path: '/filmType',
    component: Layout,
    redirect: '/filmType',
    name: 'filmType',
    meta: {
      title: '影视类型',
      single: true
    },
    children: [
      {
        path: '/filmType',
        name: 'filmType',
        component: () => import('@/pages/filmType/index.vue'),
        meta: { title: '影视类型' },
      }
    ],
  } */
];
