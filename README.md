
## Proje Yönetim Sistemi | Backend
Bu projede Java + Spring Boot kullanılmıştır. Giriş işlemi için JWT token kullanılmıştır. 
3 mikroservis mevcut:
 - **User Mikroservisi**
 - **Proje Mikroservisi**
 - **Görev Mikroservisi**
 - ve hepsini bağlayan bir **Gateway**
 
 Tüm bağlantılar Gateway'in 8080 portu üzerinden yönlendiriliyor.
## API Kullanımı

### USER MİKROSERVİS

#### Tüm üyeleri getir

```http
  GET /api/users
```

#### Projeye bağlı üyeleri getir

```http
  GET /api/users/project/{id}
```
#### Göreve bağlı üyeleri getir

```http
  GET /api/users/task/{id}
```


#### Üye Ol

```http
  POST /api/users/register
```

| Parametre   | Tip      | Açıklama                                              |
| :---------- | :------- | :---------------------------------------------------- |
| `name`      | `string` | **Gerekli**. Kullanıcının adı                         |
| `surname`   | `string` | **Gerekli**. Kullanıcının soyadı                      |
| `mail`      | `string` | **Gerekli**. Kullanıcının e-posta adresi              |
| `password`  | `string` | **Gerekli**. Kullanıcının şifresi                     |
| `phone`     | `string` | **Gerekli**. Kullanıcının telefon numarası            |
| `role`      | `string` | **Postmande Gerekli**. Kullanıcının rolü (`PROJECT_MANAGER` vb.) |
| `status`    | `string` | **İsteğe bağlı**. Kullanıcının durumu (`AVAILABLE` vb.) |
| `projectId` | `number` | **İsteğe bağlı**. Kullanıcının bağlı olduğu proje ID'si |
| `gorevId`   | `number` | **İsteğe bağlı**. Kullanıcının görev ID’si            |

Arayüzden yapılan kayıtlar default olarak Geliştirici olarak kaydedilir. Sisteme tam erişim için postman üzerinden `PROJECT_MANAGER` yetkisiyle bir kullanıcı oluşturun. Şifrenin 8 haneden uzun olmasına dikkat edin.

| Yetkiler (`role`)      | Açıklama                             |
| :-------------------- | :----------------------------------- |
| `PROJECT_MANAGER`                | Her yetkiye tam erişim             |
| `DEVELOPER`         | Sadece kendi görevinin durumunu ve kendi aktiflik durumunu güncelleyebilme           |
| `GUEST`        | Gözlemci, mevcut proje ve görevleri gözlemleyebilir |

NOT: Bu yetki kısıtlamaları frontend tarafında kontrol ediliyor. Endpointler için de tanımlanması daha sağlıklı olacaktır.

#### Giriş yap

```http
  POST /api/users/login
```
  | Parametre   | Tip      | Açıklama                                              |
| :---------- | :------- | :---------------------------------------------------- |
| `mail`      | `string` | **Gerekli**. Kullanıcının e-posta adresi              |
| `password`  | `string` | **Gerekli**. Kullanıcının şifresi                     |

Response :
`{
    "token": JWT_TOKEN",
    "id": 15,
    "role": "PROJECT_MANAGER"
}`


#### Kullanıcı Güncelle

```http
  PUT /api/users
```

| Parametre   | Tip      | Açıklama                                                  |
| :---------- | :------- | :-------------------------------------------------------- |
| `id`        | `number` |  Kullanıcının benzersiz ID değeri             |
| `name`      | `string` |  Kullanıcının adı                             |
| `surname`   | `string` |  Kullanıcının soyadı                          |
| `mail`      | `string` |  Kullanıcının e-posta adresi                  |
| `password`  | `string` |  Kullanıcının şifresi                         |
| `phone`     | `string` |  Kullanıcının telefon numarası                |
| `role`      | `string` |  Kullanıcının rolü (`PROJECT_MANAGER` vb.)    |
| `status`    | `string` |  Kullanıcının durumu (`AVAILABLE`, `UNAVAILABLE`) |
| `projectId` | `number` |  Kullanıcının bağlı olduğu proje ID’si        |
| `gorevId`   | `number` |  Kullanıcının atanmış görev ID’si             |


#### Kullanıcı Silme

```http
  DELETE /api/users/{id}
```

### PROJE MİKROSERVİSİ

#### Tüm öğeleri getir

```http
  GET /api/projects
```

#### Proje Oluştur

```http
  POST /api/projects
```

| Parametre      | Tip      | Açıklama                                      |
| :------------- | :------- | :-------------------------------------------- |
| `projectName`  | `string` | **Gerekli**. Oluşturulacak projenin adı       |
| `projectDate`  | `string` | **Gerekli**. Projenin başlangıç tarihi (YYYY-MM-DD formatında) |


#### Proje Güncelle

```http
  PUT /api/projects
```

| Parametre      | Tip      | Açıklama                                                |
| :------------- | :------- | :------------------------------------------------------ |
| `id`           | `number` | **Gerekli**. Projenin benzersiz kimlik numarası         |
| `projectName`  | `string` | **Gerekli**. Oluşturulacak projenin adı                 |
| `projectDate`  | `string` | **Gerekli**. Projenin başlangıç tarihi (YYYY-MM-DD formatında) |

#### Proje Sil

```http
  DELETE /api/projects/{id}
```

### TASK MİKROSERVİS

#### Tüm öğeleri getir

```http
  GET /api/tasks
```


#### Görev oluştur

```http
  POST /api/tasks
```

| Parametre           | Tip      | Açıklama                                                         |
| :------------------ | :------- | :---------------------------------------------------------------- |
| `task_name`         | `string` | **Gerekli**. Görevin adı                                          |
| `status`            | `string` | **Gerekli**. Görevin durumu (`TODO`, `IN_PROGRESS`, `NEEDS_REVIEW`, `FINISHED`) |
| `task_description`  | `string` | **Gerekli**. Görevle ilgili detaylı açıklama                      |
| `assignedProjectId` | `number` | **Gerekli**. Görevin ait olduğu proje ID’si                       |
| `assignedUserId`    | `number` | **Gerekli**. Görevin atanacağı kullanıcının ID’si                 |

#### Görev Güncelle

```http
  PUT /api/tasks
```

| Parametre           | Tip      | Açıklama                                                         |
| :------------------ | :------- | :---------------------------------------------------------------- |
| `id`                | `number` | **Gerekli**. Görevin benzersiz kimlik numarası                    |
| `task_name`         | `string` | **Gerekli**. Görevin adı                                          |
| `status`            | `string` | **Gerekli**. Görevin durumu (`TODO`, `IN_PROGRESS`, `NEEDS_REVIEW`, `FINISHED`) |
| `task_description`  | `string` | **Gerekli**. Görevle ilgili detaylı açıklama                      |
| `assignedUserId`    | `number` | **Gerekli**. Görevin atanacağı kullanıcının ID’si                 |


| Durum (`status`)      | Açıklama                             |
| :-------------------- | :----------------------------------- |
| `TODO`                | Görev yapılmayı bekliyor             |
| `IN_PROGRESS`         | Görev üzerinde çalışılıyor           |
| `NEEDS_REVIEW`        | Görev tamamlandı, incelenmeyi bekliyor |
| `FINISHED`            | Görev tamamlandı ve onaylandı        |


#### Görev Sil

```http
  DELETE /api/tasks{id}
```

