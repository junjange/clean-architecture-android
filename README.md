# π‘ Topic
Clean Architectureμ λν΄μ κ³΅λΆνλ©΄μ μ λ¦¬ν μ½λμ΄λ©° clean architecture base code μλλ€.

## π Summary
ν΄λ¦° μν€νμ²λ μ°λ¦¬κ° λ§λλ μ νλ¦¬μΌμ΄μμ κ²½μ° μλ§μ κΈ°λ₯λ€μ΄ μκ³  κ·Έκ²λ€μ λ°λ₯Έ λ³΅μ‘λκ° λμμ μ΄λ¬ν λμ λ³΅μ‘λλ₯Ό μ μ§λ³΄μνκΈ° μ½κ³  μ½λλ₯Ό μ’λ κ³ νμ§λ‘ ν₯μμν€κΈ° μν΄μ λμ¨ κ΅¬μ‘°λΌκ³  νλ€.


- Clean Architecture κ΅¬μ‘°
    - Entities
        - λΉμ§λμ€ κ·μΉμ μΊ‘μν νκ². λ©μλλ₯Ό κ°λ κ°μ²΄μΌμλ μκ³  λ°μ΄ν° ν΄λμ€μ ν¨μμ μ§ν©μΌ μλ μλ€.
        - κ°μ₯ μΌλ°μ μΈ μΊ‘μνμ΄λ©° μΈλΆκ° λ³κ²½λλλΌκ³  κ·μΉμ΄ λ³κ²½μ΄ λ  κ°λ₯μ±μ΄ μ μ κ²λ€.
    - Use Cases
        - μ μ€μΌμ΄μ€λ μ νλ¦¬μΌμ΄μμ κ³ μ  κ·μΉμ μΊ‘μν νλ©° μν°ν°λ‘λΆν° λ°μ΄ν° νλ¦κ³Ό μ‘°ν©μ νλ€.
        - μ μ€μΌμ΄μ€ κ³μΈ΅μ λ³κ²½μ μν°ν°μ μν₯μ μ£Όλ©΄ μλλ©° DB, νλ μμν¬ λ° UIμ λν λ³κ²½μΌλ‘λΆν° λΆλ¦¬ λμ΄μΌ νλ€.
    - Interface Adapters (Presenters)
        - μΈν°νμ΄μ€ μ΄λν°λ μν°ν°μ μ μ€μΌμ΄μ€μ νΈλ¦¬ν νμμμ DB λλ μΉμ μ μ©ν  μ μλ νμμΌλ‘ λ³νν κ².
        - MVP μμμ Presenter, MVVM μμμ ViewModel μ΄ ν¬ν¨λλ€. (μ¦, μμ λΉμ¦λμ€ λ‘μ§λ§μ λ΄λΉνλ μ­ν)
    - Frameworks & Drivers (Web, DB)
        - νλ μμν¬μ λλΌμ΄λ²λ μμν μ λ³΄λ€μ κ°μ§λ€. μΉ νλ μμν¬, DB, UI, HTTP Client, API λ±μΌλ‘ κ΅¬μ±λ κ°μ₯ λ°κΉ₯μͺ½ κ³μΈ΅ (μλ²μμ API ν΅μ  λΆλΆμ΄ λ  μ μμκ² κ°λ€.)
    - μ΄λ¬ν ν΄λ¦° μν€νμ²λ‘ λμνκΈ° μν΄μλ μμ‘΄μ± κ·μΉμ μ λ°λΌμΌ νλ€. μ¦, κ°κ°μ ν΄λμ€λ νκ°μ§ μ­νλ§ μνμ νκ³ , μλ‘ μμ‘΄ κ΄κ³λ₯Ό μ΄λ»κ² ν μ§μ λν κ·μΉμ΄ μ ν΄μ Έ μμΌλ©° μ΄λ₯Ό μ§μΌμ€μΌ νλ€.
    - λν μμ‘΄μ± κ·μΉμ μΈλΆ β λ΄λΆ, μ μμ€ β κ³ μμ€ μΌλ‘ ν₯ν΄μΌ νλ€. λ°λΌμ μμ κ·Έλ¦Όμμ λ³΄λ©΄ λ΄λΆλ‘ κ°μλ‘ μμ‘΄μ±μ λ?μμ ΈμΌ νλ€.
    - ViewModel λ±μ λΉμ¦λμ€ λ‘μ§μ DB, Web κ³Ό κ°μ μΈλΆμ μΈ μ¬ν­λ€μ λν΄μ μμ‘΄νμ§ μμμΌ νλ€.


- Clean Architecture μ μ₯μ 
    - μλ‘μ΄ κΈ°λ₯μ λΉ λ₯΄κ² μ μ©μν¬ μ μλ€.
    - μ§μ€νλ ν΄λμ€μ λ°λΌμ νλ‘μ νΈμ μ μ§λ³΄μ λ° κ΄λ¦¬κ° μ©μ΄ν΄μ§λ€.
    - ν¨ν€μ§ κ΅¬μ‘° νμμ΄ μ¬μμ§λ€.
    - νμ€νΈ μ½λ μμ±μ΄ μ©μ΄νλ€.


- Android μμμ Clean Architecture
    - μλλ‘μ΄λ μμμ ν΄λ¦° μν€νμ²λ₯Ό μ μ©ν λ μλ Presentation, Domain, Data μ΄λ κ² 3κ°μ κ³μΈ΅μΌλ‘ λλ μ§κ² λλ€.
    - Presentation β Domain, Data β Domain μ΄λ° λ°©ν₯μΌλ‘ μμ‘΄μ±μ κ°μ§κ³  μλ€. (β : λ°λΌλ³Έλ€.)
    - Presentation
        - νλ©΄ λ° μμΆλ ₯μ λν UI μ κ΄λ ¨λ λΆλΆμ λ΄λΉνλ€.
        - μ¦, Activity, Fragment, View, Presenter, ViewModel λ±μ΄ ν¬ν¨λ  κ² κ°λ€.
    - Domain
        - μ νλ¦¬μΌμ΄μμ λΉμ¦λμ€ λ‘μ§μμ νμν UseCase, Model μ ν¬ν¨νλ λΆλΆμ΄λ€.
        - UseCase μ κ²½μ° κ°κ°μ κ°λ³μ μΈ κΈ°λ₯μ΄λ λΉμ¦λμ€ λΌλ¦¬ λ¨μμ΄λ©° Presentation, Data κ³μΈ΅μ λν μμ‘΄μ±μ κ°μ§μ§ μκ³  λλ¦½μ μΌλ‘ λΆλ¦¬ λμ΄μΌ νλ€.
        - μμ κ·Έλ¦Όμ λ³΄λ©΄ Repository λΆλΆλ ν¬ν¨μ΄ λμ΄ μλ€.
    - Data
        - λλ©μΈμ λν μμ‘΄μ±μ κ°μ§κ³  μλ κ³μΈ΅μ΄λ©° λλ©μΈ κ³μΈ΅μ Repository κ΅¬νμ²΄λ₯Ό ν¬ν¨νκ³  μλ€.
        - DB, Web, HTTP Client (μλ²μμ API ν΅μ ) λ±μ΄ μ΄ κ³μΈ΅μ λ€μ΄κ°λ€.
        - λν mapper ν΄λμ€λ₯Ό ν΅ν΄μ λ°μ΄ν° κ³μΈ΅μ λͺ¨λΈμ λλ©μΈ κ³μΈ΅μ λͺ¨λΈλ‘ λ³νμμΌμ£Όλ μ­νλ νκ² λλ€.


- μ μ©ν΄λ³Έ ν΄λ¦° μν€νμ² κ΅¬μ‘° μμ
    - ν¨ν€μ§ κ΅¬μ‘°
        - di (μμ‘΄μ± κ΄λ ¨ λΆλΆ)
            - Database Module (Room λλ Realm μ μ¬μ©ν  κ²½μ° μμ‘΄μ± μ£Όμ κ΄λ ¨ λΆλΆ)
            - Network Module (Api Module β μλ²μμ API ν΅μ μ λν μμ‘΄μ± μ£Όμ κ΄λ ¨ λΆλΆ)
            - App Module (Adapter λ± μ΄μ  μ±λ΄μμ μμ‘΄μ± κ΄λ ¨ν λΆλΆ)

        - util (μΆκ°μ μΌλ‘ μ¬μ©ν  κ²λ€, LoadingDialog, Constant λΆλΆ)

        - Presentation Layer (UI, λΉμ¦λμ€ λ‘μ§κ³Ό μ§μ μ μΌλ‘ κ΄λ ¨λ λΆλΆ)
            - adapter (Adapter κ΄λ ¨λ λΆλΆ)
            - views (Activity & Fragment κ΄λ ¨λ λΆλΆ)
            - viewModel (ViewModel κ΄λ ¨λ λΆλΆ)
            - base (Base Setting λΆλΆ)

        - Domain Layer (λΉμ¦λμ€ λ‘μ§μμ νμν Entity, Usecase, Repository λΆλΆ)
            - model (Data classμ Model μ κ΄λ ¨λ λΆλΆ)
            - usercase (APIμ λν interface κ΄λ ¨λ λΆλΆ)

        - Data Layer (HTTP Client, DB, SP, Interceptor κ³Ό κ΄λ ¨λ λΆλΆ)
            - mapper (λλ©μΈ κ³μΈ΅μ model κ΄λ ¨ν΄μ mapping μμκ³Ό κ°μ κ²λ€μ λν λΆλΆ)
            - sharedpreference (λ΄λΆ λ°μ΄ν°μ κ΄λ ¨λ λΆλΆ)
            - DB (Room, Realm λ΄μ₯ DB μ κ΄λ ¨λ λΆλΆ)
            - interceptor (μλ²μμ ν΅μ μ΄ ν ν°λ° μ¬λ¬κ°μ§ interceptor λΆλΆ)
            - repository (Usecase μ λν λμμ μ μνλ λΆλΆ)


- κ° νμΌμ΄ νλ μ­ν 
    - NavHostActivity -> BaseActivityλ₯Ό μμλ°μΌλ©° κ° Fragmentλ₯Ό μ°κ²°νλ€.
                       + μ°κ²°μ κ²½μ° Navigation/navigation_graph.xml μμ μ€μ ν ν μ¬μ©νλ€.
    - Common -> Base, Di, Util, widget : Base μΈν, μμ‘΄μ±, λΆκ°μ μΈ κΈ°λ₯
    - κ° Page(Ex. Home) -> HomeFragment, HomeViewModel, HomeNavigationAction, HomeActionHandler
                         + RecyclerView μ κ°μ κΈ°λ₯μ΄ μμ΄ adpaterλ₯Ό μ¬μ©ν΄μΌνλ€λ©΄ TodoAdapter, TodoViewHolder μ¬μ©
                         + λ μ΄μμμ λ΄κ° μνλ λ©μλλ₯Ό λ§λ€κ³  μΆλ€λ©΄ HomeBidingAdaters μ¬μ©
    - BaseResult -> API ν΅μ  κ²°κ³Ό νμΈ
    - Use case -> usecase/UsecaseOrganizedNote μ°Έκ³ 
    - Repository -> λ΄κ° νν μκ³  μλ retrofitμ νΈμΆνλ repository μ λ€λ₯΄κ² μμ²­ κ°μνκ² μ λ¦¬λμ΄ μμ..
                    μλ§ μλ²μ μ°κ²°λλ λΆλΆμ dataμμ μ΄λ£¨μ΄μ§λ―? κ·Έλ λ€λ©΄ μμ BaseResultμ dataκ° μ°κ²°λλ λΆλΆμΌμλ?!..
                    dataμ domainμ Repostitory κ΅¬μ±μ΄ λκ°λ€!?..
                    => μ΄ λΆλΆμ docs/DataOrganizedNoteλ₯Ό νμΈνλ©΄ λ λ―
    - Model -> λ΄κ° νν μκ³  μλ model
    - usecase : μ¬μ©μκ° μμ²­ν  μ μλ μλΉμ€(κΈ°λ₯)
    - Repository : κΈ°λ₯μ Interfaceμμ μ μ
    - RepositoryImpl : κΈ°λ₯μ κ΅¬ν
    - DataSource : APIλ₯Ό Interfaceμμ μ μ
    - DataSourceImpl : API κ΅¬ν
    - TempApiService : API νΈμΆ


- νλ¦
    - Activity/Fragment/Adapter -> ViewModel -> UseCase -> Repository -> RepositoryImpl -> DataSource -> DataSourceImpl -> ApiService
    - Presentation <-> Domain <-> Data
    
    
## π οΈ ****Tech Stack****

- Kotlin
- Data binding
- Room
- Coroutine
- Flow
- Retrofit2 + OkHttp
- List Adapter + DiffUtil
- Moshi
- Hilt
- DataStore
- Jetpack Navigation

## ****βοΈΒ Architecture****

- Clean Architecture
- MVVM
    

