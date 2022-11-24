<template>
<div>

    <div class="one">
        <div class="two">
            <p>녹화가 완료되었습니다.</p>
        <p>저장할 제목을 입력해주세요.</p>
        <form @submit.prevent="onSubmit" class="formGroup">
            <div class="box">
                <label for="title">제목</label>
                <input type="text" class="form-control title" id="title" placeholder="제목을 입력해주세요." v-model="title" size="320px" maxlength="20">
            </div>
            <div class="submit">
                <button type="submit" class="Btn">저장</button>
            </div>
        </form>

    </div>

  </div>
</div>
</template>
<script>
import { mapActions } from 'vuex';

export default{ 
    name:'ModalView',
    components:{},
    props:{
        url:String,
    },
    data(){
        return{
            title: '',
        };
    },
    setup(){},
    created(){
        
    },
    mounted(){},
    unmounted(){},
    methods:{
        ...mapActions(['saveRecording', 'issueToken']),
        async onSubmit(){
            if(!this.title){
                alert('저장할 제목을 입력해주세요!')
            }
            await this.issueToken()
            this.saveRecording({
                fileName: this.title,
                URL: this.url,
            })
        }
    }
}
</script>

<style scoped>
.one{
    position: fixed;
    top: 200px;
    left: 50%;
    transform: translate(-50%, 0%);
    width: 320px;
    height: 300px;
    display: flex;
    align-items: center;
    border-radius: 5px;
    border: solid 2px #BF4F37;
    box-shadow: 1px 1px 1px gray;
    background-color: white;
}

.two{ 
    z-index: 10;
    opacity: 1;
    margin: auto auto;
}

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
.Btn {
background-color:var(--color-red-2);
height: 40px;
width: 300px;
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