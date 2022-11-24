<template>
<real-header></real-header>
  <div class="board-update-box">
    <board-head-slider></board-head-slider>
    <div class="title">
        <router-link :to="{name: 'boardMain', params:{category_id: categoryId}}">
            <i class="bi bi-arrow-left-circle-fill" style="color:#FFD39B; font-size: 24px; margin:0 16px"></i>
        </router-link>
        <p>{{parents[categoryId-1].name}}</p>
    </div>

    <article-form
        v-if="isArticle"
        :article="article"
        :category_pk="this.categoryId"
        action="update"
    ></article-form>
  </div>
</template>
<script>

import BoardHeadSlider from '@/components/board/BoardHeadSlider.vue'
import ArticleForm from '@/components/board/ArticleForm.vue';
import { mapActions, mapGetters } from 'vuex';
import RealHeader from '@/components/RealHeader.vue';
export default{ 
    name:'BoardUpdate',
    components:{ArticleForm, BoardHeadSlider, RealHeader},
    data(){
        return{
            categoryId: this.$route.params.category_id,
            article_pk: this.$route.params.board_id
        }
    },
    computed: {
        ...mapGetters(['article', 'isArticle', 'parents'])
    },
    setup(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['fetchArticle'])
    },
    created(){
        this.fetchArticle(this.article_pk)
    },
}
</script>

<style scoped>
.board-update-box{
    width: 100%;
}
@media(max-width:760px){
  .board-update-box{
    width: 100vw;
  }
}
.title{
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #FF843E;
}
.title p{
  font-size: 20px;
  font-weight: bold;
  color: #FFB400;
  margin: 0 16px;

}
</style>