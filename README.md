# Spring AI Alibaba DeepSeek 示例

本示例展示了如何使用 `spring-ai-alibaba` Starter 将 DeepSeek 接入 Spring Boot 应用。包含基础的配置类、REST API 和启动类，可直接在本地启动测试。

## 快速开始

1. **配置密钥**：在环境变量中写入 DeepSeek 的 API Key。

   ```bash
   export DEEPSEEK_API_KEY=你的密钥
   ```

2. **构建与运行**：

   Maven Central 暂未提供 `spring-ai-alibaba` 相关依赖，项目的 `pom.xml` 已配置 [Aliyun 公共仓库](https://maven.aliyun.com/repository/public) 以保证依赖可以解析。如果本地曾经缓存了解析失败的记录，请使用 `-U` 强制刷新：

   ```bash
   mvn -U spring-boot:run
   ```

3. **调用接口**：向 `/api/chat` 发送 POST 请求，Body 示例：

   ```json
   {
     "prompt": "介绍一下DeepSeek"
   }
   ```

   返回结果示例：

   ```json
   {
     "response": "DeepSeek 是..."
   }
   ```

## 关键代码

- `pom.xml`：引入 `spring-ai-alibaba-starter-deepseek` Starter。
- `DeepseekConfig`：创建 `DeepSeekApi`、`DeepSeekChatModel` 和 `ChatClient`，并配置默认的记忆能力。
- `ChatController`：暴露 `/api/chat` 接口，使用模板包装用户输入并调用 DeepSeek。

更多配置项可在 `application.yml` 中调整，例如模型名称、温度和最大 tokens。
