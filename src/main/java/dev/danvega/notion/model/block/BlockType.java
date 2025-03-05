package dev.danvega.notion.model.block;

/**
 * Enum of Notion block types.
 */
public enum BlockType {
    PARAGRAPH("paragraph"),
    HEADING_1("heading_1"),
    HEADING_2("heading_2"),
    HEADING_3("heading_3"),
    BULLETED_LIST_ITEM("bulleted_list_item"),
    NUMBERED_LIST_ITEM("numbered_list_item"),
    TO_DO("to_do"),
    TOGGLE("toggle"),
    CODE("code"),
    CHILD_PAGE("child_page"),
    CHILD_DATABASE("child_database"),
    EMBED("embed"),
    IMAGE("image"),
    VIDEO("video"),
    FILE("file"),
    PDF("pdf"),
    BOOKMARK("bookmark"),
    CALLOUT("callout"),
    QUOTE("quote"),
    EQUATION("equation"),
    DIVIDER("divider"),
    TABLE_OF_CONTENTS("table_of_contents"),
    COLUMN("column"),
    COLUMN_LIST("column_list"),
    LINK_PREVIEW("link_preview"),
    SYNCED_BLOCK("synced_block"),
    TEMPLATE("template"),
    LINK_TO_PAGE("link_to_page"),
    TABLE("table"),
    TABLE_ROW("table_row"),
    UNSUPPORTED("unsupported");

    private final String value;

    BlockType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}