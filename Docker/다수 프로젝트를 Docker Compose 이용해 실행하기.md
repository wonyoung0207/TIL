## 디렉터리 구성 

- 두 프로젝트를 띄우는 환경(개발 환경 vs 운영/스테이징 환경)에 따라 docker compose 구동 

```
project/
├── compose.yaml
├── .env
├── backend/
├── frontend/
└── database/
    ├── init/
    │   ├── 01-schema.sql
    │   ├── 02-data.sql
    │   └── 03-grants.sql
    └── conf.d/
        └── charset.cnf
```