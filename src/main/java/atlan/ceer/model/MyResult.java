package atlan.ceer.model;

public class MyResult {
    private Object content;
    private boolean status;
    private String message;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MyResult(boolean status) {
        this.status = status;
    }

    public MyResult() {
    }

    public MyResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public MyResult(Object content, boolean status, String message) {
        this.content = content;
        this.status = status;
        this.message = message;
    }

    public MyResult(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public MyResult(Object content, boolean status, String message, String code) {
        this.content = content;
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "content=" + content +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
