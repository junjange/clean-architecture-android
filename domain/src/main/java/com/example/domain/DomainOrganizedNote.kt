package com.example.domain

/**
Domain 정리 글
결국 Domain 코드들을 확인해보면 다음과 같은 구성인 것을 확인할 수 있다.
- BaseResult -> ???
- Use case -> usecase/UsecaseOrganizedNote 참고
- Repository -> 내가 흔히 알고 있는 retrofit을 호출하는 repository 와 다르게 엄청 간소하게 정리되어 있음..
                아마 서버와 연결되는 부분은 data에서 이루어질듯? 그렇다면 위에 BaseResult와 data가 연결되는 부분일수도?!..
                data와 domain에 Repostitory 구성이 똑같다!?..
                이 부분은 data/DataOrganizedNote를 확인하면 될듯
- Model -> 내가 흔히 알고 있는 model

진행 방향 ->
Presentation/HomeFragment <-> usecase <-> Repostiory(domain) <-> PhotoRepositoryImpl(data) : PhotoRepository(domain)

Presentation에서 사용자가 사용하는 기능울 usecase에 정의, 정의된 기능을 Interface를 통해 보관해 둔 것이 Repository, 그것을 구현시키는 곳이 RepositoryImpl

 */