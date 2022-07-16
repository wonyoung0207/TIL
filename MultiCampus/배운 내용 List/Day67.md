# Day67

---

> Final Project 

# Final Project

>main page menu 수정 
>
>review rank 페이지 완성 

- Review SQL 문 LIMIT 이용 
  - LIMIT : 시작위치, 반환갯수 
    - ex) LIMIT 3,2  => 3의 위치에서 2개의 값을 가져온다. 
    
      ```sql
      SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
      		m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
      		g.name as gname, g.tid as hgid,
      		ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt
      		FROM movie m
      		INNER JOIN genre g ON m.gid=g.id
      		INNER JOIN reviews r ON r.mid=m.id
      		GROUP BY id
      		ORDER BY star DESC
      		LIMIT 0,5
      ```

- Review SQL문 서브쿼리 이용

  ```sql
  SELECT COUNT(*) FROM (
  SELECT m.id, ROUND(AVG(r.star),1) as star 
  FROM movie m
  INNER JOIN reviews r ON r.mid=m.id
  GROUP BY id) c
  ```

- Rank 페이지 Next, Prev

  - 방법

    1. Next 와 Prev 버튼 누를 경우 mnum 이라는 숫자를 가져올 정보의 숫자만큼 증가,감소시킨다. 

    2. Controller 에서 넘겨받은 mnum을 이용해 기존 Select 쿼리 Limit 에 증가,감소된 mnum을 넣어 계속해서 다른 정보를 뺴온다. 

    3. 빼온 정보를 같은 화면의 list에 넣어 이전 페이지의 정보와는 다른 정보를 뿌려준다. 

    ```html
    <tr class="rates rates--top"
        th:each="movie,index : ${starSortList}">
        <td class="rates__obj">
            <a href="#" class="rates__obj-name" th:text="(${index.index}+1+${mnum})+'. '+${movie.title}" th:href="@{/movielist/detail(id=${movie.id})}"></a></td>
        <td class="rates__vote" th:text="${movie.rcnt}+' reviews'">
            reviews</td>
        <td class="rates__result" th:text="${movie.star}">5.0</td>
        <td class="rates__stars">
            <div class="col-sm-4"></div>
            <div class="col-sm-8">
                <div class="movie__rate">
                    <div class="star-ratings">
                        <div name="${movie.star}"
                             class="star-ratings-fill space-x-4 text-lg"
                             th:style="${'width:'+movie.star*20+'%'}">
                            <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                        <div class="star-ratings-base space-x-4 text-lg">
                            <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    
    
    
    
    <div class="pagination paginatioon--full coloum-wrapper">
        <a th:href="@{/reviewrank(mnum=${mnum}-5)}"
           th:if="${mnum}-5 >= 0" class="pagination__prev">prev</a> 
        <a th:href="@{/reviewrank(mnum=${mnum}+5)}" th:if="${mnum}+5 <= ${rankcnt}" class="pagination__next">next</a>
    </div>
    ```

    ```java
    @RequestMapping("/reviewrank")
    public String reviewrank(Model m,Integer mnum) {
        int page = 0;
        int rankcnt = 0;
        List<MovieVO> starSortList = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
    
        try {
            rankcnt = mbiz.selectrankcnt();// 현재 리뷰가 있는 영화의 갯수중 중복제거 갯수 - 몇순위 까지 있는지  
            
            if (mnum != null) {// 페이지 넘김 버튼 클릭시 호출 
                page = mnum;
                map.put("mnum1", page);
                map.put("mnum2", 5);// 3개의 정보만 가져온다. 
                starSortList = mbiz.selectrankpage(map);
            } else if(mnum == null) {// 처음 reviewrank 페이지 들어왔을 경우
                page = 0;
                map.put("mnum1", page);
                map.put("mnum2", 5);
                starSortList = mbiz.selectrankpage(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        m.addAttribute("mnum", page);
        m.addAttribute("center", "/reviewrank/rank");
        m.addAttribute("starSortList", starSortList);//rank 순위별 5개 정보저장 
        m.addAttribute("rankcnt", rankcnt); // rank 가 총 몇개인지 저장 - > NEXT 버튼 처리시 사용 
        return "index";
    }
    ```

    ```sql
     <select id="selectrankpage" parameterType="map" resultType="movieVO">
                SELECT m.id, m.gid, m.title, m.director, m.mainactor, m.releasedate,
                m.posterimg1, m.posterimg2, m.country, m.runningtime, m.text,
                g.name as gname, g.tid as hgid,
                ROUND(AVG(r.star),1) as star ,COUNT(mid) as rcnt
                FROM movie m
                INNER JOIN genre g ON m.gid=g.id
                INNER JOIN reviews r ON r.mid=m.id
                GROUP BY id
                ORDER BY star DESC
                LIMIT #{mnum1},#{mnum2}
    </select>
    ```

    
