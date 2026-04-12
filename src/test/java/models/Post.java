package models;

public class Post {
    private int userId;
    private Integer id; // Integer (obiekt), bo przy tworzeniu (POST) serwer nam go nada
    private String title;
    private String body;

    // Pusty konstruktor - wymagany przez biblioteki do mapowania JSON
    public Post() {}

    // Konstruktor do tworzenia nowych postów (bez ID, bo ID nada serwer)
    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    // Gettery i Settery (RestAssured ich używa, żeby czytać dane z obiektu)
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}