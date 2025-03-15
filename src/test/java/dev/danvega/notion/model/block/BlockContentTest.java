package dev.danvega.notion.model.block;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.danvega.notion.model.block.content.*;
import dev.danvega.notion.model.common.RichText;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the type-safe block content model.
 */
public class BlockContentTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateParagraphBlock() {
        // Given
        String text = "This is a paragraph";
        
        // When
        Block block = Block.paragraph(text);
        
        // Then
        assertThat(block).isNotNull();
        assertThat(block.getType()).isEqualTo("paragraph");
        assertThat(block.getContent()).isInstanceOf(ParagraphContent.class);
        
        ParagraphContent content = block.getParagraphContent();
        assertThat(content).isNotNull();
        assertThat(content.getRichText()).hasSize(1);
        assertThat(content.getRichText().get(0).getPlainText()).isEqualTo(text);
    }
    
    @Test
    void shouldCreateHeadingBlock() {
        // Given
        String text = "This is a heading";
        
        // When
        Block h1Block = Block.heading(text, 1);
        Block h2Block = Block.heading(text, 2);
        Block h3Block = Block.heading(text, 3);
        
        // Then
        assertThat(h1Block.getType()).isEqualTo("heading_1");
        assertThat(h2Block.getType()).isEqualTo("heading_2");
        assertThat(h3Block.getType()).isEqualTo("heading_3");
        
        assertThat(h1Block.getContent()).isInstanceOf(HeadingContent.class);
        assertThat(h2Block.getContent()).isInstanceOf(HeadingContent.class);
        assertThat(h3Block.getContent()).isInstanceOf(HeadingContent.class);
        
        HeadingContent h1Content = h1Block.getHeadingContent();
        assertThat(h1Content).isNotNull();
        assertThat(h1Content.getRichText()).hasSize(1);
        assertThat(h1Content.getRichText().get(0).getPlainText()).isEqualTo(text);
        assertThat(h1Content.getLevel()).isEqualTo(1);
        
        HeadingContent h2Content = h2Block.getHeadingContent();
        assertThat(h2Content.getLevel()).isEqualTo(2);
        
        HeadingContent h3Content = h3Block.getHeadingContent();
        assertThat(h3Content.getLevel()).isEqualTo(3);
    }
    
    @Test
    void shouldCreateBulletedListItem() {
        // Given
        String text = "Bulleted item";
        
        // When
        Block block = Block.bulletedListItem(text);
        
        // Then
        assertThat(block.getType()).isEqualTo("bulleted_list_item");
        assertThat(block.getContent()).isInstanceOf(BulletedListItemContent.class);
        
        BulletedListItemContent content = block.getBulletedListItemContent();
        assertThat(content).isNotNull();
        assertThat(content.getRichText()).hasSize(1);
        assertThat(content.getRichText().get(0).getPlainText()).isEqualTo(text);
    }
    
    @Test
    void shouldCreateNumberedListItem() {
        // Given
        String text = "Numbered item";
        
        // When
        Block block = Block.numberedListItem(text);
        
        // Then
        assertThat(block.getType()).isEqualTo("numbered_list_item");
        assertThat(block.getContent()).isInstanceOf(NumberedListItemContent.class);
        
        NumberedListItemContent content = block.getNumberedListItemContent();
        assertThat(content).isNotNull();
        assertThat(content.getRichText()).hasSize(1);
        assertThat(content.getRichText().get(0).getPlainText()).isEqualTo(text);
    }
    
    @Test
    void shouldCreateToDoItem() {
        // Given
        String text = "To-do item";
        
        // When
        Block uncheckedBlock = Block.toDo(text, false);
        Block checkedBlock = Block.toDo(text, true);
        
        // Then
        assertThat(uncheckedBlock.getType()).isEqualTo("to_do");
        assertThat(uncheckedBlock.getContent()).isInstanceOf(ToDoContent.class);
        
        ToDoContent uncheckedContent = uncheckedBlock.getToDoContent();
        assertThat(uncheckedContent).isNotNull();
        assertThat(uncheckedContent.getRichText()).hasSize(1);
        assertThat(uncheckedContent.getRichText().get(0).getPlainText()).isEqualTo(text);
        assertThat(uncheckedContent.getChecked()).isFalse();
        
        ToDoContent checkedContent = checkedBlock.getToDoContent();
        assertThat(checkedContent.getChecked()).isTrue();
    }
    
    @Test
    void shouldCreateCodeBlock() {
        // Given
        String code = "System.out.println(\"Hello, world!\");";
        String language = "java";
        
        // When
        Block block = Block.code(code, language);
        
        // Then
        assertThat(block.getType()).isEqualTo("code");
        assertThat(block.getContent()).isInstanceOf(CodeContent.class);
        
        CodeContent content = block.getCodeContent();
        assertThat(content).isNotNull();
        assertThat(content.getRichText()).hasSize(1);
        assertThat(content.getRichText().get(0).getPlainText()).isEqualTo(code);
        assertThat(content.getLanguage()).isEqualTo(language);
    }
    
    @Test
    void shouldCreateImageBlock() {
        // Given
        String imageUrl = "https://example.com/image.jpg";
        
        // When
        Block block = Block.imageFromUrl(imageUrl);
        
        // Then
        assertThat(block.getType()).isEqualTo("image");
        assertThat(block.getContent()).isInstanceOf(ImageContent.class);
        
        ImageContent content = block.getImageContent();
        assertThat(content).isNotNull();
        assertThat(content.getType()).isEqualTo(BlockType.IMAGE);
        assertThat(content.getExternal()).isNotNull();
        assertThat(content.getExternal().getUrl()).isEqualTo(imageUrl);
    }
    
    @Test
    void shouldGetRichTextFromDifferentBlockTypes() {
        // Given
        String text = "Test text";
        List<Block> blocks = List.of(
            Block.paragraph(text),
            Block.heading(text, 1),
            Block.bulletedListItem(text),
            Block.numberedListItem(text),
            Block.toDo(text, false),
            Block.code(text, "plain")
        );
        
        // When/Then
        for (Block block : blocks) {
            List<RichText> richText = block.getRichText();
            assertThat(richText).isNotNull();
            assertThat(richText).hasSize(1);
            assertThat(richText.get(0).getPlainText()).isEqualTo(text);
        }
    }
}