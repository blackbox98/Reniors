<template>
  <form @submit.prevent="onSubmit" class="commentForm" v-if="isLogginedIn">
    <img class="person" :src="currentUser.baseURL+currentUser.userProfile" alt="person">
    <label for="comment"></label>
    <input type="text" id="content" placeholder="내용을 입력해주세요." v-model="content">
    <button type="submit">등록</button>
  </form>
  <form class="commentForm" v-else>
    <img src="@/assets/basicperson2.png" alt="logo" class="person">
    <label for="comment"></label>
    <input type="text" id="content" placeholder="댓글작성은 로그인 후 이용해주세요." readonly>
    <button>등록</button>
  </form>

</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default{ 
    name:'CommentForm',
    components:{},
    data(){
        return{
            content: '',
        }
    },
    computed: {
        ...mapGetters(['article','currentUser', 'isLogginedIn'])
    },
    setup(){},
    created(){
    },
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['createComment']),
        onSubmit() {
            if(!this.content){
                alert('내용을 입력해주세요!')
            } else{
            this.createComment({
                boardId: this.$route.params.board_id,
                categoryId: this.$route.params.category_id,
                content: this.content
                })
            this.content= ''
            this.$router.go()
            }
        }
    }
}
</script>

<style scoped>
.commentForm{
    height: 64px;
    display: flex;
    margin: 8px 4px;
    align-items: center;
    justify-content: space-between;
}
.commentInput{
   width: 500px;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
input{
    width: 75%;
    border: solid 1px #8A8A8A;
    height: 90%;
    border-radius: 5px;
    padding: 4px;
}

button{
    height: 90%;
    background-color: #FFC0A3;
    font-size: 16px;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 0 50px;
}

button:hover{
    background-color: #ff8432;
}
.person{
    width: 50px;
    height: 50px;
    border-radius: 50%;
    
}
@media(max-width:760px){
    button{
        padding: 10px;
    }
    .person{
        width: 32px;
        height: 32px;
    }
}
</style>