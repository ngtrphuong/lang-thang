# Authentication API Document

## Table of contents

* [Đăng nhập](#đăng-nhập)
* [Đăng nhập với Google](#đăng-nhập-bằng-tài-khoản-Google)
* [Lấy lại Token mới](#Lấy-lại-Token-mới)
* [Đăng ký](#Đăng-ký)
* [Quên mật khẩu](#Quên-mật-khẩu)

## Đăng nhập

----
Trả về token hợp lệ

* **URL**: `/auth/login`

* **Method:** `POST`

* **Request Params**

  | Name         | Type     | Description          |
  | ----------   |:------:  | ------------         |
  | `email`      | `string` | Email của người dùng |
  | `password`   | `string` | Mật khẩu             |

* **Success Response:**

    - **Code:** 200 <br />
      **Content:** `chuỗi token` kèm theo cookie được gắn vào

    * **Example:**
    ```json5
    {
    'token': 'asdasdas21312$#...',
    'status': 200,
    'duration': '10000 - hạn sử dụng của token tính bằng ms'
    }
  ```


* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Email không đúng định dạng" }`

    * **Code:** 422 UNPROCESSABLE_ENTITY <br />
      **Content:** `{ error : "Sai email hoặc mật khẩu" }`

    * **Example:**
    ```json5
    {
    "timestamp": "2021-05-06T07:50:25.212+00:00",
    "status": 422,
    "error": "Unprocessable Entity",
    "message": "Bad credentials",
    "path": "/auth/login"
    }
  ```

## Đăng nhập bằng tài khoản Google

----
Trả về token hợp lệ

- Nếu người dùng chưa đăng nhập lần nào thì sẽ tự động tạo một tài khoản dựa trên các thông tin công khai của tài khoản
  Google với mật khẩu ngẫu nhiên, mật khẩu này sẽ được gửi qua email cho người dùng. Sau đó tiến hành đăng nhập bình
  thường

- Nếu người dùng đã đăng nhập thì tiến hành đăng nhập bình thường không cần dùng mật khẩu

* **URL**: `/auth/google`

* **Method:** `POST`

* **Request Params**

  | Name         | Type     | Description          |
      | ----------   |:------:  | ------------         |
  | `google_token`      | `string` | Google Token của người dùng |

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `chuỗi token` kèm theo cookie được gắn vào
    
    * **Example**: xem lại ở phần [Đăng nhập](#đăng-nhập)

* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ message : "Google token không hợp lệ" }`

## Lấy lại Token mới

----
Trả về lại một token hợp lệ khác sau khi đã đăng nhập

* **URL**: `/auth/refreshToken`

* **Method:** `POST`

* **Header**: `Authorization: Bearer <token hiện tại>`

* **Cookie**: `refresh-token: Mã refresh token được server gắn vào sau khi đăng nhập thành công`

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `chuỗi token mới` kèm theo cookie mới được gắn vào
        
    * **Example**: xem lại ở phần [Đăng nhập](#đăng-nhập)

* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ message : "Google token không hợp lệ" }`

    * **Code:** 403 ACCESS_DENIED <br />
      **Content:** `{ message : "chưa có chuỗi token trong header" }`

    * **Code:** 422 UNPROCESSABLE_ENTITY <br />
      **Content:** `{ message : "refresh-token đã hết hạn hoặc không hợp lệ" }`

## Đăng ký

----
Đăng ký tài khoản với hệ thống

* **URL**: `/auth/registration`

* **Method:**: `POST`

* **URL Params**

| Name         | Type     | Description          |
| ----------   |:------:  | ------------         |
| `name`      | `string` | Tên hiển thị |
| `email`      | `string` | Email của người dùng |
| `password`   | `string` |   Mật khẩu           |
| `matchedPassword`   | `string` |   Mật khẩu nhập lại lần 2 |

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `"OK"`

    * **Example**:
    ```json5
    {
     'message': "OK",
     'status': 200,
     "timestamp": "2021-05-06T07:50:25.212+00:00",
    }
  ```

* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Email không đúng định dạng / Mật khẩu trên dưới không khớp nhau" }`

    * **Code:** 409 CONFLICT <br />
      **Content:** `{ error : "Email đã được sử dụng" }`

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "Email đã đăng ký nhưng chưa kích hoạt " }`

## Quên mật khẩu

----
Thay đổi mật khẩu mới khi quên mật khẩu

* **URL**: `/auth/resetPassword`

* **Method:**: `POST`

* **URL Params**

| Name         | Type     | Description          |
| ----------   |:------:  | ------------         |
| `email`      | `string` | Email của người dùng |

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `"OK"`

    * **Example**: xem lại ở phần [Đăng ký](#đăng-ký)
    
* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ message : "Email không đúng định dạng" }`

    * **Code:** 403 FORBIDDEN <br />
      **Content:** `{ error : "Email không tồn tại" }`