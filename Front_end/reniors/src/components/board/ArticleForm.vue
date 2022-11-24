<template>
    <div>  
      <form @submit.prevent="onSubmit" class="formGroup" >
        <div class="box">
          <label for="title">제목</label>
          <input type="text" class="form-control title" id="title" placeholder="제목을 입력해주세요." v-model="title" size="320px" maxlength="50">
        </div>
        <div class="box">
          <label for="content">내용</label>
          <textarea type="text" class="form-control content" id="content" placeholder="내용을 입력해주세요." v-model="contents"></textarea>
        </div>
        <div class="submit">
            <button type="submit" class="Btn">등록</button>
        </div>
      </form>
    </div>

</template>
<script>
import { mapActions } from 'vuex';

export default{ 
    name:'ArticleForm',
    components:{},
    props: {
        article: Object,
        action: String,
    },
    data(){
        return{
            category_pk: this.$route.params.category_id,
            title: this.article.title,
            contents: this.article.contents,

        };
    },
    setup(){},
    created(){},
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['createArticle', 'updateArticle']),
        onSubmit(){
            if(!this.title && !this.contents || (!this.title && this.contents)){
                alert('제목을 입력해주세요.')
            } else if(!this.contents){
                alert('내용을 입력해주세요.')
            }
            if (this.action === 'create') {
                this.createArticle({ 
                    categoryId: this.category_pk,
                    contents: this.contents,
                    title: this.title,
                })
            } else if (this.action === 'update') {
                this.updateArticle({
                    categoryId: this.category_pk,
                    article_pk: this.article.boardId,
                    title: this.title,
                    contents: this.contents,

                })
            }
        }
    }
}
</script>

<style scoped>
label{
    display: flex;
    justify-content: start;
    font-size: 14px;
    color: #3A3A3A;
}
.formGroup{
    margin: 8px 4px 0 4px;
}
.box{
    margin: 4px;
}
.title{
    height: 40px;
}
.content{
    height: 360px;
    padding: 8px 10px 8px 10px;
}

.Btn {
background-color:var(--color-red-2);
height: 40px;
width: 340px;
margin: 8px 0;
border-radius: 10px;
border: none;
color: white;
font-weight: bold;
font-size: 16px;

/* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
cursor: pointer;
}

  
  </style>