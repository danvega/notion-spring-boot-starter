package dev.danvega.notion.autoconfigure;

import dev.danvega.notion.client.NotionClient;
import dev.danvega.notion.service.NotionBlockService;
import dev.danvega.notion.service.NotionDatabaseService;
import dev.danvega.notion.service.NotionPageService;
import dev.danvega.notion.service.NotionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class NotionAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(NotionAutoConfiguration.class));

    @Test
    void autoConfigurationShouldNotProvideBeansWhenApiKeyIsMissing() {
        contextRunner.run(context -> {
            assertThat(context).doesNotHaveBean(NotionClient.class);
            assertThat(context).doesNotHaveBean(NotionService.class);
            assertThat(context).doesNotHaveBean(NotionPageService.class);
            assertThat(context).doesNotHaveBean(NotionDatabaseService.class);
            assertThat(context).doesNotHaveBean(NotionBlockService.class);
        });
    }

    @Test
    void autoConfigurationShouldProvideBeansWhenApiKeyIsConfigured() {
        contextRunner
                .withPropertyValues("notion.api.key=test-key")
                .run(context -> {
                    assertThat(context).hasSingleBean(NotionClient.class);
                    assertThat(context).hasSingleBean(NotionService.class);
                    assertThat(context).hasSingleBean(NotionPageService.class);
                    assertThat(context).hasSingleBean(NotionDatabaseService.class);
                    assertThat(context).hasSingleBean(NotionBlockService.class);
                });
    }

    @Test
    void autoConfigurationShouldRespectExistingBeans() {
        contextRunner
                .withPropertyValues("notion.api.key=test-key")
                .withBean(NotionClient.class, () -> null)
                .withBean(NotionService.class, () -> null)
                .withBean(NotionPageService.class, () -> null)
                .withBean(NotionDatabaseService.class, () -> null)
                .withBean(NotionBlockService.class, () -> null)
                .run(context -> {
                    assertThat(context).hasSingleBean(NotionClient.class);
                    assertThat(context).hasSingleBean(NotionService.class);
                    assertThat(context).hasSingleBean(NotionPageService.class);
                    assertThat(context).hasSingleBean(NotionDatabaseService.class);
                    assertThat(context).hasSingleBean(NotionBlockService.class);
                });
    }
}