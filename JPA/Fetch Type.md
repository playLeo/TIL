##Fetch Type이란?

JPA가 하나의 Entity를 조회할 때, 연관관계가 있는 객체들을 어떻게 가져올 것이냐를 나타내는 설정값

1. 즉시 로딩(EAGER) 연관된 데이터(Entitiy)까지 한 번에 불러오기
2. 지연 로딩(LAZY) - 필요한 시점에 연관된 데이터(Entity) 가져오기(getter) 


## 즉시 로딩(EAGER)


@OneToMany(mappedBy)
@ManyToOne(Fetch type)
@Transactional(readOnly)
