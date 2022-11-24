<template>
  <div class="p-3 calender-box">

    <FullCalendar :options="calendarOptions" :header="{center: 'title'}" />
    <div style="float: right; text-align: right;">
      <p class="markinfo" style="margin-top: 10px;"><i class="bi bi-circle-fill" style="color: #ffb252;"></i>  관심 공고마감</p>
      <p class="markinfo" style=""><i class="bi bi-circle-fill" style="color: #37bf99;"></i>  면접</p>
    </div>
  
    <br>
    <br>
    <calendar-modal :show="showModal" @close="showModal = false">
      <template #body>
        <p v-if="this.category" style="font-size: 16px; text-align: left; font-weight: 700; color: #37bf99; margin-bottom: 7px">{{ this.title }}</p>
        <p v-if="this.company" style="font-size: 16px; text-align: left; font-weight: 700; color: #ffb252; margin-bottom: 7px">{{ this.title }}</p>
        <p v-if="this.category" style="font-size: 14px; text-align: left; color: #121212; margin-bottom: 2px"><span style="color: #9b9b9b">지원 직무&nbsp;&nbsp;&nbsp;</span>{{this.category}}</p>
        <p v-if="this.category" style="font-size: 14px; text-align: left; color: #121212; margin-bottom: 2px"><span style="color: #9b9b9b">면접 시간&nbsp;&nbsp;&nbsp;</span>{{
        (Number(this.date.slice(11,13))- 9)>=0?
        (Number(this.date.slice(11,13))- 9):
        (Number(this.date.slice(11,13))- 9+24)
        %24}} : {{ this.date.slice(14,16) }}</p>
        <p v-if="this.company" style="font-size: 14px; text-align: left; color: #121212; margin-bottom: 2px"><span style="color: #9b9b9b">회사명&nbsp;&nbsp;&nbsp;</span>{{this.company}}</p>
        <br v-if="this.company">
        <a v-if="this.company" :href="link" style="font-size: 12px; float: right; color: #86C4F1; text-decoration: none;">공고 바로가기 <i class="bi bi-link-45deg"></i></a>

      </template>
    </calendar-modal>
  </div>
</template>

<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import kocale from '@fullcalendar/core/locales/ko'

import CalendarModal from '@/components/user/CalendarModal.vue'
import dayjs from 'dayjs'
import 'dayjs/locale/ko'


// import esLocale from '@fullcalendar/core/locales/es';


export default {
  name: 'MyCalendar',
  components: {
    FullCalendar,
    CalendarModal // make the <FullCalendar> tag available
  },
  props: {
    interview: Array,
    bookmarksdate: Array,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        // locales: [koLocale, esLocale],
        locale: kocale,
        eventSources : [
          {
            events: this.interview,
            color: "#37bf99" 
          },
          {
            events: this.bookmarksdate,
            color: "#ffb252",
          }
          ], 

        eventClick: (event) => {
          this.showModal = true
          this.title = event.event._def.title
          this.date = dayjs(event.event._instance.range.start).format("YYYY-MM-DD HH:mm:ss")
          console.log(this.date);
          console.log(event.event)
          if (event.event.extendedProps.child){
            this.category = event.event.extendedProps.child
          } else {
            this.category = ''
          }
          
          if (event.event.extendedProps.link){
            this.link = event.event.extendedProps.link
          } else {
            this.link = ''
          } 
          
          if (event.event.extendedProps.company) {
            this.company = event.event.extendedProps.company
          } else {
            this.company = ''
          }
          
        },
        height: 450,
        headerToolbar: {
          left: 'prev',
          center: 'title',
          right: 'next'
        }
        // themeSystem: 'bootstrap5'
      
      },
      showModal: false,
      title: '',
      date: '',
      category: '',
      link: '',
      company: '',


    }
  },

  created(){
  },
  methods: {
    handleDateClick: function() {
      
    }
  }
}
</script>

<style>
  .calender-box{
    width: 900px;
  }
  table {
    border-collapse: collapse;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0,0,0,.25);
  }

  .fc-col-header-cell-cushion  {
    text-decoration: none;
    color: #37bf99;
    font-size: 14px;
  }

  .fc-daygrid-day-number {
    text-decoration: none;
    color: #6D6D6D;
    font-size: 12px;
  }

  .fc-event-title {
    font-size: 12px;
  }

  .fc-toolbar-title {
    font-weight: 700;
    color: #ffb252;
  }

  th{
    background-color: #f9f9f9;
  }

  .fc .fc-button-primary {
    background-color: #ffe5c5;
    border-style: none;
    box-shadow: rgba(17, 17, 26, 0.1) 0px 1px 0px;
    width: 30px;
    height: 30px;
    font-size: 12px;
    font-weight: 900;

  }

  .fc-toolbar-chunk > button:hover{
    background-color: var(--color-red-1) !important;
  }

  .fc-event-title-container {
    border-radius: 20px;
    display: flex;
    justify-content: center;
    
    
  }

  .fc-event-title {
    font-size: 10px;
  }

  .fc-daygrid-event {
    border-radius: 20px;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  }
  .markinfo {

    margin-bottom: 0px;
    font-size: 12px;
    color: #9B9B9B;
  }

  .fc-event-time {
    opacity: 0;
  }
  .fc-event-time + .fc-event-title {
    opacity: 0;
  }

  @media(max-width: 760px){
    .calender-box{
      width: 312px;
    }
  }
</style>