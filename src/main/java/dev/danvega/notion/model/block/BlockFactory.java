package dev.danvega.notion.model.block;

import dev.danvega.notion.model.block.content.BulletedListItemContent;
import dev.danvega.notion.model.block.content.CodeContent;
import dev.danvega.notion.model.block.content.HeadingContent;
import dev.danvega.notion.model.block.content.ImageContent;
import dev.danvega.notion.model.block.content.NumberedListItemContent;
import dev.danvega.notion.model.block.content.ParagraphContent;
import dev.danvega.notion.model.block.content.ToDoContent;
import dev.danvega.notion.model.common.RichText;

import java.util.List;

/**
 * Factory class for creating different types of Notion blocks.
 */
public class BlockFactory {

    private BlockFactory() {
        // Utility class, no public constructor
    }

    /**
     * Creates a paragraph block with plain text.
     *
     * @param text the text content
     * @return the paragraph block
     */
    public static Block paragraph(String text) {
        return Block.paragraph(text);
    }

    /**
     * Creates a paragraph block with rich text.
     *
     * @param richText the rich text content
     * @return the paragraph block
     */
    public static Block paragraph(List<RichText> richText) {
        ParagraphContent content = new ParagraphContent(richText);
        return new Block("paragraph", null, false, content);
    }

    /**
     * Creates a heading block with plain text.
     *
     * @param text the text content
     * @param level the heading level (1, 2, or 3)
     * @return the heading block
     */
    public static Block heading(String text, int level) {
        return Block.heading(text, level);
    }

    /**
     * Creates a heading block with rich text.
     *
     * @param richText the rich text content
     * @param level the heading level (1, 2, or 3)
     * @return the heading block
     */
    public static Block heading(List<RichText> richText, int level) {
        HeadingContent content = new HeadingContent(richText, level);
        return new Block("heading_" + level, null, false, content);
    }

    /**
     * Creates a bulleted list item with plain text.
     *
     * @param text the text content
     * @return the bulleted list item block
     */
    public static Block bulletedListItem(String text) {
        return Block.bulletedListItem(text);
    }

    /**
     * Creates a bulleted list item with rich text.
     *
     * @param richText the rich text content
     * @return the bulleted list item block
     */
    public static Block bulletedListItem(List<RichText> richText) {
        BulletedListItemContent content = new BulletedListItemContent(richText);
        return new Block("bulleted_list_item", null, false, content);
    }

    /**
     * Creates a numbered list item with plain text.
     *
     * @param text the text content
     * @return the numbered list item block
     */
    public static Block numberedListItem(String text) {
        return Block.numberedListItem(text);
    }

    /**
     * Creates a numbered list item with rich text.
     *
     * @param richText the rich text content
     * @return the numbered list item block
     */
    public static Block numberedListItem(List<RichText> richText) {
        NumberedListItemContent content = new NumberedListItemContent(richText);
        return new Block("numbered_list_item", null, false, content);
    }

    /**
     * Creates a to-do item with plain text.
     *
     * @param text the text content
     * @param checked whether the item is checked
     * @return the to-do block
     */
    public static Block toDo(String text, boolean checked) {
        return Block.toDo(text, checked);
    }

    /**
     * Creates a to-do item with rich text.
     *
     * @param richText the rich text content
     * @param checked whether the item is checked
     * @return the to-do block
     */
    public static Block toDo(List<RichText> richText, boolean checked) {
        ToDoContent content = new ToDoContent(richText, checked);
        return new Block("to_do", null, false, content);
    }

    /**
     * Creates a code block with plain text.
     *
     * @param code the code content
     * @param language the programming language
     * @return the code block
     */
    public static Block code(String code, String language) {
        return Block.code(code, language);
    }

    /**
     * Creates a code block with rich text.
     *
     * @param richText the rich text content
     * @param language the programming language
     * @return the code block
     */
    public static Block code(List<RichText> richText, String language) {
        CodeContent content = new CodeContent(richText, language);
        return new Block("code", null, false, content);
    }

    /**
     * Creates an image block from an external URL.
     *
     * @param url the image URL
     * @return the image block
     */
    public static Block imageFromUrl(String url) {
        return Block.imageFromUrl(url);
    }

    /**
     * Creates an image block from an external URL with caption.
     *
     * @param url the image URL
     * @param caption the caption for the image
     * @return the image block
     */
    public static Block imageFromUrl(String url, List<RichText> caption) {
        ImageContent content = ImageContent.ofExternal(url);
        content.setCaption(caption);
        return new Block("image", null, false, content);
    }
}