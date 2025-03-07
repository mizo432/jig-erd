package jig.erd.domain.primitive;

import java.util.List;

public class Column {
    String name;
    String alias;
    String refId;

    Entity entity;

    public Column(String name, String refId, Entity entity) {
        this.name = name;
        this.refId = refId;
        this.entity = entity;
    }

    public static Column generate(ColumnIdentifier columnIdentifier, String refId, Entity entity) {
        return new Column(columnIdentifier.columnName, refId, entity);
    }

    String label() {
        if (alias == null) return name;
        return alias;
    }

    public boolean matches(Entity entity) {
        return this.entity.matches(entity);
    }

    public String recordNodeLabelText() {
        return String.format("<%s> %s", refId, label());
    }

    public String htmlNodeLabelText() {
        return String.format("<TR><TD PORT=\"%s\" ALIGN=\"LEFT\">%s</TD></TR>", refId, label());
    }

    String edgeNodeText() {
        // node:port
        // portは""で囲ってはいけない
        return String.format("%s:%s", entity.nodeIdText(), refId);
    }

    public boolean matches(ColumnIdentifier columnIdentifier) {
        return this.entity.matches(columnIdentifier.toEntityIdentifier()) && this.name.equals(columnIdentifier.columnName);
    }

    public Entity entity() {
        return entity;
    }

    public String readableLabel() {
        return entity.readableLabel() + '.' + name;
    }

    public boolean matchesSchema(List<Schema> schemas) {
        return entity.matchesSchema(schemas);
    }
}
