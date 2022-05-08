package ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.AncapActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.ColorizedTextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.TextFactory;

public class ActionBarBuilderImpl implements ActionBarBuilder {

    private TextFactory textFactory;

    private Text text;

    public ActionBarBuilderImpl() {
        this(new ColorizedTextFactory());
    }

    public ActionBarBuilderImpl(TextFactory factory) {
        this.textFactory = factory;
    }

    public ActionBarBuilderImpl(ActionBarBuilderImpl builder) {
        this(builder.getTextFactory());
    }

    protected TextFactory getTextFactory() {
        return this.textFactory;
    }

    @Override
    public ActionBarBuilder setText(String text) {
        this.setText(this.getTextFactory().buildFrom(text));
        return this;
    }

    protected void setText(Text text) {
        this.text = text;
    }

    @Override
    public ActionBar build() {
        return new AncapActionBar(this.text);
    }
}
