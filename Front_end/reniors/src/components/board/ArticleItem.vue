<template>
  <div class="box">
    <router-link :to="{ name: 'boardDetail', params: {'board_id': article.boardId, 'category_id': category_id}}">
        <div class="contents">
            <p  class="title">{{article.title}}</p>
            <div class="bottom">
                <img src="" alt="">
                <p  class="userName">{{article.userName}} |</p>
                <p class="time">{{article.updatedAt.slice(0,4)}}.{{article.updatedAt.slice(5,7)}}.{{article.updatedAt.slice(8,10)}}</p>
            </div>
        </div>

    </router-link>
  </div>
</template>
<script>

export default{ 
    name:'ArticleItem',
    props:{
        article: Object,
    },
    data(){
        return{
            'category_id' : this.$route.params.category_id,
        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{},
}
</script>

<style scoped>
.box{
    height: 80px;
    border-bottom: solid 2px;
    border-bottom-color: #FFD39B;
    display: flex;
}
.box a{
    text-decoration: none;
    color: black;
    display: flex;
    justify-content: start;
    align-items: center;
}
.title{
    margin: 0 8px;
    font-size: 16px;
    display: flex;
    justify-content: start;

}
.bottom{
    display: flex;
}
.userName{
    margin: 0 8px;
    font-size: 14px;
    color: #6D6D6D;
    display: flex;
    justify-content: start;
    align-items: center;

}
.time{
    font-size: 12px;
    color: #6D6D6D;
    margin: 0;
    display: flex;
    justify-content: start;
    align-items: center;

}
</style>