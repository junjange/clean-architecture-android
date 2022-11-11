package com.example.data

/**
Data 정리 글

음음 일단 1차적으로 작성
진행 방향 ->
domain/usecase/GetPhotoListUseCase : PhotoRepository(domain) <-> PhotoRepositoryImpl(data) : PhotoRepository(domain) <-> PhotoDataSource <-> PhotoDataSourceImpl : PhotoDataSource <-> PhotoApiService

그런데 di/Modul에서도 PhotoRepository(domain)을 상속받아 사용하는데 어째서인지는 Di에 대해서 공부를 해야할듯

22.10.28.create
- domain의 usecase에서 사용자의 서비스(기능)이 실행이 되면 그것을 구현시키기 위해 data의 Repository가 실행됨
- RepositoryImpl는  domain의 Repository(Interface) 상속받는다. => Repository에 기능들을 RepositoryImpl에서 구현한다고 생각하면 될듯
- 다음 RepositroyImpl에서 DataSourceImpl를 호출한다. => API 기능 명세를 호출한다고 생각하면 될듯
- 호출된 DataSourceImpl는 DataSource(Interface)를 상속받는다. -> DataSource에 기능들을 DataSourceImpl에서 구현한다고 생각하면 된다.
- DataSourceImpl에서 TempApiService의 API를 호출하게 되는 것이다.

위에 내용을 보게되면 비슷한 구조가 있는 것을 확인할 수 있다.
usecase -> Repository(Interface) -> RepositoryImpl ->
DataSource(Interface) -> DataSourceImpl -> TempApiService

- usecase : 사용자가 요청할 수 있는 서비스(기능)
- Repository : 기능을 Interface에서 정의
- RepositoryImpl : 기능을 구현
- DataSource : API를 Interface에서 정의
- DataSourceImpl : API 구현
- TempApiService : API 호출


 */