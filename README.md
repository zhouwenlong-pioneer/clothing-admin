# 服装商品后台管理系统 - 后端

基于 Spring Boot 3.x 的服装商品管理系统后端服务。

## 技术栈

- **框架**: Spring Boot 3.2.0
- **安全**: Spring Security + JWT 认证
- **ORM**: MyBatis-Plus 3.5.5
- **数据库**: MySQL 8.0+
- **接口文档**: Knife4j (Swagger 3)
- **其他**: Lombok, Spring Validation

## 项目结构

```
backend/
├── src/main/
│   ├── java/com/clothing/admin/
│   │   ├── ClothingAdminApplication.java  # 启动类
│   │   ├── common/                        # 公共类
│   │   │   ├── Result.java                # 统一响应格式
│   │   │   └── PageResult.java            # 分页响应
│   │   ├── config/                        # 配置类
│   │   │   ├── SecurityConfig.java        # Spring Security 配置
│   │   │   ├── JwtAuthenticationFilter.java # JWT 认证过滤器
│   │   │   ├── JwtUtil.java               # JWT 工具类
│   │   │   ├── MybatisPlusConfig.java     # MyBatis-Plus 配置
│   │   │   ├── SwaggerConfig.java         # Knife4j 配置
│   │   │   └── PasswordConfig.java        # 密码编码器配置
│   │   ├── controller/                    # 控制器
│   │   │   ├── AuthController.java        # 认证接口
│   │   │   ├── DashboardController.java   # 首页看板
│   │   │   ├── ProductController.java     # 商品管理
│   │   │   ├── ProductSkuController.java  # SKU 管理
│   │   │   ├── CategoryController.java    # 分类管理
│   │   │   ├── InventoryController.java   # 库存管理
│   │   │   ├── OrderController.java       # 订单管理
│   │   │   └── MemberController.java      # 会员管理
│   │   ├── dto/                           # 数据传输对象
│   │   │   ├── LoginDTO.java
│   │   │   ├── LoginVO.java
│   │   │   ├── ProductDTO.java
│   │   │   ├── ProductSkuDTO.java
│   │   │   └── InventoryDTO.java
│   │   ├── entity/                        # 实体类
│   │   │   ├── User.java                  # 用户
│   │   │   ├── Product.java               # 商品
│   │   │   ├── ProductSku.java            # 商品 SKU
│   │   │   ├── Category.java              # 分类
│   │   │   ├── Inventory.java             # 库存
│   │   │   ├── InventoryLog.java          # 库存流水
│   │   │   ├── Order.java                 # 订单
│   │   │   ├── OrderItem.java             # 订单项
│   │   │   └── Member.java                # 会员
│   │   ├── exception/                     # 异常处理
│   │   │   ├── BusinessException.java     # 业务异常
│   │   │   └── GlobalExceptionHandler.java # 全局异常处理
│   │   ├── mapper/                        # Mapper 接口
│   │   └── service/                       # 服务层
│   └── resources/
│       ├── application.yml                # 应用配置
│       └── schema.sql                     # 数据库初始化脚本
└── pom.xml
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE clothing_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 导入表结构：
```bash
mysql -u root -p clothing_admin < src/main/resources/schema.sql
```

3. 修改 `application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/clothing_admin
    username: your_username
    password: your_password
```

### 启动项目

```bash
# 编译打包
mvn clean install

# 直接运行
mvn spring-boot:run

# 或运行 jar 包
java -jar target/admin-1.0.0.jar
```

服务启动后访问：
- **API 地址**: http://localhost:8080/api
- **接口文档**: http://localhost:8080/api/doc.html

## API 接口

### 认证接口

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | /auth/login | 用户登录 |
| GET | /auth/info | 获取当前用户信息 |
| POST | /auth/logout | 用户登出 |

### 首页看板

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /dashboard/stats | 获取统计数据 |
| GET | /dashboard/sales-trend | 销售趋势 |
| GET | /dashboard/inventory-warning | 库存预警 |

### 商品管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /product/page | 商品列表（分页） |
| GET | /product/{id} | 商品详情 |
| POST | /product | 创建商品 |
| PUT | /product | 更新商品 |
| DELETE | /product/{id} | 删除商品 |
| POST | /product/{id}/status | 上下架商品 |

### SKU 管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /product-sku/{productId} | 获取商品 SKU 列表 |
| POST | /product-sku | 创建 SKU |
| PUT | /product-sku | 更新 SKU |
| DELETE | /product-sku/{id} | 删除 SKU |

### 分类管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /category/tree | 分类树 |
| POST | /category | 创建分类 |
| PUT | /category | 更新分类 |
| DELETE | /category/{id} | 删除分类 |

### 库存管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /inventory/{skuId} | 获取库存 |
| GET | /inventory/log/page | 库存流水 |
| POST | /inventory/in | 入库 |
| POST | /inventory/out | 出库 |
| POST | /inventory/check | 库存盘点 |

### 订单管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /order/page | 订单列表 |
| GET | /order/{id} | 订单详情 |
| POST | /order/{id}/deliver | 订单发货 |
| PUT | /order/{id}/status | 更新订单状态 |

### 会员管理

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /member/page | 会员列表 |
| GET | /member/{id} | 会员详情 |
| POST | /member | 创建会员 |
| PUT | /member | 更新会员 |
| DELETE | /member/{id} | 删除会员 |

## 认证说明

### JWT Token 使用

1. 调用 `/auth/login` 获取 token：
```json
{
  "code": 200,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "expiresIn": 86400000
  }
}
```

2. 后续请求在 Header 中携带 token：
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 默认账号

- 用户名：`admin`
- 密码：`123456`

## 统一响应格式

所有接口返回统一格式：

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

分页响应：

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [],
    "total": 100,
    "size": 10,
    "current": 1,
    "pages": 10
  }
}
```

## 开发说明

### 新增接口

1. 在 `controller/` 创建 Controller 类
2. 在 `service/` 创建服务接口和实现
3. 在 `mapper/` 创建 Mapper 接口
4. 在 `entity/` 创建实体类（如需要）
5. 在 `dto/` 创建 DTO（如需要）

### 异常处理

- 业务异常抛出 `BusinessException`
- 全局异常处理器会自动捕获并返回统一格式

### 代码规范

- 使用 Lombok 简化代码
- Controller 层只处理 HTTP 相关逻辑
- Service 层处理业务逻辑
- Mapper 层只处理数据库操作

## 配置说明

### application.yml 主要配置

```yaml
# 服务器端口和上下文路径
server:
  port: 8080
  servlet:
    context-path: /api

# JWT 配置
jwt:
  secret: your-secret-key  # 生产环境请修改
  expiration: 86400000     # token 过期时间（毫秒）

# MyBatis-Plus 配置
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # SQL 日志
```

## 注意事项

1. **生产环境** 请修改 JWT secret 密钥
2. **数据库密码** 请使用环境变量或配置中心管理
3. **SQL 日志** 生产环境请关闭
4. **默认账号** 请在部署后修改密码

## License

MIT
