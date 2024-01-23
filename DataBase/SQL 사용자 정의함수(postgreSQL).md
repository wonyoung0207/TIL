# PostgreSQL 사용자 정의 함수

## 구조

```sql
CREATE [OR REPLACE] FUNCTION 함수명 (파라미터-'데이터타입 변수명') 
RETURNS 반환_데이터명 AS $변수이름$ -- 반환값은 프로시저가 아니기 때문에 무조건 1개임 
   DECLARE
      변수 변수타입; -- 시작/종료부 안에서 사용할 변수 
      [...] -- 위 형태로 여러번 사용 가능 
   BEGIN
      < 함수구현 > -- 위에서 선언한 변수에 select 값을 저장할 수 있음 
      [.. logic]
      RETURN { 변수이름 | value }
   END; 
LANGUAGE language_name;
```

## 예시1

```sql
-- 생성 예시 
CREATE FUNCTION product(num_1 INT, num_2 INT) 
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ -- 딜리미터로, 함수의 본문을 감싸는 것을 뜻한다. 딜리미터 시작 

BEGIN
 RETURN num_1 * num_2;
END;
$$ -- 딜리미터의 끝

-- 호출 예시
SELECT product(
   num_1 => 12, 
   num_2 => 6
);
```

## 예시2

```sql
-- 선언
CREATE OR REPLACE FUNCTION ConcatenateStrings(str1 character varying, str2 character varying)
RETURNS character varying
AS $$
DECLARE
    result character varying;
BEGIN
    result := str1 || ' ' || str2;
    RETURN result;
END;
$$ LANGUAGE plpgsql;

-- 호출
SELECT ConcatenateStrings('Hello', 'World') AS ConcatenatedString;
```

## 예시3

```sql
CREATE OR REPLACE FUNCTION public.get_alramrtsp(eventno bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$ -- 딜리미터 시작 
declare   
   rtspalias varchar;
   eCameraId varchar;
BEGIN          
    select trim(camera_id) 
    into eCameraId
    from tb_ids_event
    where no = eventNo;

    if eCameraId like '%CAM%' then
        rtspalias := eCameraId;
    elsif eCameraId like '%SND%' then
        select alert_description
        into rtspalias
        from tb_ids_event
        where no = eventNo;
       
       if rtspalias = null then
           select rtsp_alias
           into rtspalias
      from tb_ids_camera_info
      where link_code = linkcode
      and camera_id like '%CAM%'
      order by
      ABS(view_position - vposition)
      limit 1;
       end if;
    else
        select rtsp_alias
        into rtspalias
        from tb_ids_camera_info
        where camera_id = eCameraId;
    end if;

    return rtspalias;

END;
$function$ -- 딜리미터 끝 
;
```



