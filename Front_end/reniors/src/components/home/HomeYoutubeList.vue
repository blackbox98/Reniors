<template>
  <div>
    <h2 class="youtube-type">{{ type }} <i class="bi bi-youtube"></i></h2>
    <ul class="youtube-list">
      <li class="youtube-item" v-for="(item, index) in items" :key="index">
        <a :href="item.url">
          <img :src="item.thumbnail" />
          <div>
            <p class="hi">{{ item.title }}</p>
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HomeYoutubeList",
  props: {
    type: String,
    youtubes: Array,
  },
  setup(props) {
    const YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    const items = [];
    const dumpUrl = [
      {
        title:
          "[2022년 최신] 50대 이후에도 일하고 싶다면 이 영상 꼭 시청하세요!!! ㅣ 시니어들에게 가장 인기많고 취업하기 좋은 자격증 7가지를 모아보았습니다",
        thumbnail: "https://i1.ytimg.com/vi/Qt1qK_tsYpQ/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=Qt1qK_tsYpQ",
      },
      {
        title: "《꿀팁》 월50~250만노인일자리 찾는 5가지 노하우!",
        thumbnail: "https://i1.ytimg.com/vi/5BcVt2ToXwM/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=5BcVt2ToXwM",
      },
      {
        title:
          "중장년 추천 직업 30가지! 계속 일하고 싶으신 중장년 보세요~(인생 2막, 새로운 도전)",
        thumbnail: "https://i1.ytimg.com/vi/0rYlQzGrerA/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=0rYlQzGrerA",
      },
      {
        title: "노인일자리 허드렛일 뿐?｜직접알려드립니다",
        thumbnail: "https://i1.ytimg.com/vi/P8_DERBH0J8/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=P8_DERBH0J8",
      },
      {
        title:
          '40대 50대 60대 추천 일자리! 정부 복지 정책 일자리로 잘 알려지지 않은 "노인맞춤형돌봄서비스 생활지원사"에 대해서 알려드립니다.',
        thumbnail: "https://i1.ytimg.com/vi/5U175vSLZPY/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=5U175vSLZPY",
      },
      {
        title:
          "시니어취업! 노인 일자리 제도 속, 나에게 딱 맞는 일자리 어디서 찾을 수 있을까?",
        thumbnail: "https://i1.ytimg.com/vi/tbfKOS5fHPs/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=tbfKOS5fHPs",
      },
      {
        title: "인생 2막의 시작, 시니어 취업! 달콤한 정보쇼 꿀단지 3회",
        thumbnail: "https://i1.ytimg.com/vi/EguXw9UxUKc/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=EguXw9UxUKc",
      },
      {
        title:
          '[시니어 교육] 인생2막 준비를 위한 "자기찾기" (한국노인인력개발원)',
        thumbnail: "https://i1.ytimg.com/vi/c49dWMe9M7U/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=c49dWMe9M7U",
      },
      {
        title: "은퇴 자격증 추천 | 55년~65년생 남자 추천 가성비 甲 자격증 3종",
        thumbnail: "https://i1.ytimg.com/vi/WhuzGhqN5K0/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=WhuzGhqN5K0",
      },
      {
        title: "[tbs TV] 노인취업, 나도 일하고 싶다",
        thumbnail: "https://i1.ytimg.com/vi/C9VEAFbZFiY/mqdefault.jpg",
        url: "https://www.youtube.com/watch?v=C9VEAFbZFiY",
      },
    ];
    if (props.youtubes.length != 0) {
      props.youtubes.map((youtube) => {
        const item = {
          title: youtube.snippet.title,
          thumbnail: youtube.snippet.thumbnails.default.url,
          url: YOUTUBE_URL + youtube.id.videoId,
        };
        items.push(item);
      });
    } else {
      dumpUrl.map((youtube) => {
        const item = {
          title: youtube.title,
          thumbnail: youtube.thumbnail,
          url: youtube.url,
        };
        items.push(item);
      });
    }
    return {
      items,
    };
  },
};
</script>

<style scoped>
.youtube-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
  font-size: 20px;
}

.youtube-list {
  display: flex;
  margin: 0 8px;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
}

.youtube-list::-webkit-scrollbar {
  height: 5px;
}

.youtube-list::-webkit-scrollbar-thumb {
  background-color: var(--color-green-1);
  border-radius: 10px;
}

.youtube-list::-webkit-scrollbar-track {
  background-color: var(--color-green-3);
  border-radius: 10px;
}

.youtube-item {
  background-color: var(--color-black-1);
  border-radius: 0.3rem;
  width: 300px;
  margin: 10px;
  list-style: none;
  flex: 0 0 auto;
  background-color: white;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}

.youtube-item p {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  margin: 8px;
  font-weight: bold;
  text-align: left;
}

.youtube-item img {
  width: 300px;
  border: none;
  border-radius: 0.3rem 0.3rem 0 0;
}

.youtube-item > a {
  color: black;
  text-decoration: none;
  font-weight: 400;
}

.youtube-item > a:visited {
  color: black;
}

.bi-youtube {
  color: #c4302b;
}
</style>
