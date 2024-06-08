## 과제

user 기능을 JDBCTemplate를 이용해서 추가하기

## 목차
[1. 과제 결과](#1-과제-결과)

[2. 발생했던 오류](#2-발생했던-오류)

[3. 추가로 알게 된 점](#3-추가로-알게-된-점)

[4. 코드](#4-코드)

---

## 1. 과제 결과
<picture>
	<img src=https://github.com/heyapple25/springDay03/assets/56960059/1d8dd2db-0764-47a4-871d-5140ec41c60f width=500 height=500>
</picture>
---

## 2. 발생했던 오류
- 삭제 후 유저 목록에 빈 페이지가 발생한 오류
<picture>
	<img src=https://github.com/heyapple25/springDay03/assets/56960059/db1007c2-fa6a-44b1-aafe-6c2b099ecdb4 width=350 height=200>
</picture>

${\color{green}해결}$ : 삭제 후 목록페이지로 redirect 하지 않아서 발생했던 문제였다.

<br>

- BeanInstantiationException
<picture>
	<img src=https://github.com/heyapple25/springDay03/assets/56960059/70d13770-0ac3-4f79-96f1-57d86353979c width=1500 height=150>
</picture>

${\color{green}해결}$ : 빈 객체 초기화 오류로
<picture>
	![12412412](https://github.com/heyapple25/springDay03/assets/56960059/83f324f3-b0a2-40c6-be48-be779b7a60c5)
</picture>

이 코드를 사용할 때 문제가 발생했다. BeanPropertyRowMapper클래스를 사용할 때 VO 클래스에 기본 생성자를 넣으면 해결된다.

<br>

- @Transaction 어노테이션 작동X 오류

${\color{red}미 해결}$ : Service클래스에 @Transaction 어노테이션을 작성했지만 checked Exception이 아닌 Runtime Exception을 발생시켰는데도 jdbcTemplate 이 그대로 commit 을 발생시키는 오류

<br>

[목차로](#목차)

---

## 3. 추가로 알게 된 점
- BeanPropertyRowMapper 클래스 대신 RowMapper인터페이스를 정의한 내부나 익명클래스를 query시에 넣어줘도 된다.
```java
class userMapper implements RowMapper<UserVO>{
		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new UserVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
	}
// 글 목록 조회
	public List<UserVO> getUserList() {
		System.out.println("===> getUserList() - USERDAO 기능 처리");
		List<UserVO> userList = jdbcTemplate.query(SQL_LIST,new userMapper());
		return userList;
	}
```

[목차로](#목차)

---

## 4. 코드
- userController.java
```java
@Controller
public class UserController {
	@Autowired
	private UserService us;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/user/list.do", method=RequestMethod.GET)
	public String userList(Model m) throws SQLException{
		List<UserVO>userList=us.getList(null);
		m.addAttribute("userList",userList);
		return "user/list";
	}
	@RequestMapping(value="/user/detail.do", method=RequestMethod.GET)
	public String userDetail(UserVO u,Model m) throws SQLException{
		m.addAttribute("user",us.getOne(u));
		return "user/detail";
	}
	@RequestMapping(value="/user/delete.do", method=RequestMethod.GET)
	public String userDelete(UserVO u,Model m) throws SQLException{
		logger.info("userDelete 진행 ");
		us.delete(u);
		return "redirect:list.do";
	}
	@RequestMapping(value="/user/insert.do", method=RequestMethod.GET)
	public String userInsertPage(UserVO u,Model m) throws SQLException{
		return "user/write";
	}
	
	@RequestMapping(value="/user/update.do", method=RequestMethod.GET)
	public String userUpdatePage(UserVO u,Model m) throws SQLException{
		m.addAttribute("user",us.getOne(u));
		return "user/update";
	}
	@RequestMapping(value="/user/update.do", method=RequestMethod.POST)
	public String userUpdate(UserVO u,Model m) throws SQLException{
		us.update(u);
		return "redirect:list.do";
	}
	@RequestMapping(value="/user/insert.do", method=RequestMethod.POST)
	public String userInsert(UserVO u,Model m) throws SQLException{
		us.insert(u);
		return "redirect:list.do";
	}
}
```
- UserDAO.java
```java
@Repository("userDAO")
public class UserDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String SQL_GET = "select * from users where id=?";
	final String SQL_LIST = "select * from users";
	final String SQL_INSERT = "insert into users(id, password, name, role) " +
						"values(?,?,?,?)";
	final String SQL_UPDATE = "update users set name=?, password=?, role=? where id=?";
	final String SQL_DELETE = "delete from users where id=?";
	
	// 글등록
	public void insertUser(UserVO vo) {
		System.out.println("===> insertUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_INSERT,vo.getId(),vo.getPassword(),vo.getName(),vo.getRole());
	}
	
	// 글수정
	public void updateUser(UserVO vo) {
		System.out.println("===> updateUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_UPDATE,vo.getName(),vo.getPassword(),vo.getRole(),vo.getId());
	}
	
	// 글삭제
	@Transactional
	public void deleteUser(UserVO vo) {
		System.out.println("===> deleteUser() - UserDAO 기능 처리");
		jdbcTemplate.update(SQL_DELETE, vo.getId());	
	}

	// 글 상세 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> getBoard() - BoardDAO 기능 처리");
		Object[] objArr = new Object[]{vo.getId()};
		UserVO user= jdbcTemplate.queryForObject(SQL_GET, objArr, new BeanPropertyRowMapper<>(UserVO.class));
		return user;
	}
	
	class userMapper implements RowMapper<UserVO>{
		@Override
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new UserVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
	}
	
	// 글 목록 조회
	public List<UserVO> getUserList() {
		System.out.println("===> getUserList() - USERDAO 기능 처리");
		List<UserVO> userList = jdbcTemplate.query(SQL_LIST, new BeanPropertyRowMapper<>(UserVO.class));
		return userList;
	}
}
```
- UserVO.java
```java
public class UserVO {
	String id;
	String password;
	String name;
	String role;
	
	public UserVO() {
		this("","","","");
	}
	public UserVO(String id) {
		this(id,"","","");
	}
	public UserVO(String id, String password, String name, String role) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		return Objects.equals(id, other.id);
	}

	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
}
```
- UserServiceImpl.java
```java
@Service("userService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userdao;

	public UserVO getOne(UserVO vo) {
		return userdao.getUser(vo);
	}

	public List<UserVO> getList(UserVO vo) {
		System.out.println("getList() - BoardServiceImpl 실행");
		return userdao.getUserList();
	}

	public void insert(UserVO vo) {
		userdao.insertUser(vo);
	}

	public void update(UserVO vo) {
		userdao.updateUser(vo);
	}

	public void delete(UserVO vo) {
		userdao.deleteUser(vo);
	}
}
```
- UserService.java
```java
public interface UserService {
	UserVO getOne(UserVO vo);
	List<UserVO> getList(UserVO vo);
	void insert(UserVO vo);
	void update(UserVO vo);
	void delete(UserVO vo);
}
```
[목차로](#목차)
