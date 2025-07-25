/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.tables.CoreThemeTemplates;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreThemeTemplatesRecord extends UpdatableRecordImpl<CoreThemeTemplatesRecord> implements Record16<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>paradise_forums.core_theme_templates.template_id</code>.
     */
    public void setTemplateId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>paradise_forums.core_theme_templates.template_id</code>.
     */
    public Integer getTemplateId() {
        return (Integer) get(0);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_set_id</code>.
     */
    public void setTemplateSetId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_set_id</code>.
     */
    public Integer getTemplateSetId() {
        return (Integer) get(1);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_group</code>.
     */
    public void setTemplateGroup(String value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_group</code>.
     */
    public String getTemplateGroup() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_content</code>.
     */
    public void setTemplateContent(String value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_content</code>.
     */
    public String getTemplateContent() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_name</code>.
     */
    public void setTemplateName(String value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_name</code>.
     */
    public String getTemplateName() {
        return (String) get(4);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_data</code>.
     */
    public void setTemplateData(String value) {
        set(5, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_data</code>.
     */
    public String getTemplateData() {
        return (String) get(5);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_updated</code>.
     */
    public void setTemplateUpdated(Integer value) {
        set(6, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_updated</code>.
     */
    public Integer getTemplateUpdated() {
        return (Integer) get(6);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_removable</code>.
     */
    public void setTemplateRemovable(Integer value) {
        set(7, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_removable</code>.
     */
    public Integer getTemplateRemovable() {
        return (Integer) get(7);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_added_to</code>.
     */
    public void setTemplateAddedTo(Integer value) {
        set(8, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_added_to</code>.
     */
    public Integer getTemplateAddedTo() {
        return (Integer) get(8);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_user_added</code>.
     */
    public void setTemplateUserAdded(Integer value) {
        set(9, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_user_added</code>.
     */
    public Integer getTemplateUserAdded() {
        return (Integer) get(9);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_user_edited</code>.
     */
    public void setTemplateUserEdited(Integer value) {
        set(10, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_user_edited</code>.
     */
    public Integer getTemplateUserEdited() {
        return (Integer) get(10);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_master_key</code>.
     */
    public void setTemplateMasterKey(String value) {
        set(11, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_master_key</code>.
     */
    public String getTemplateMasterKey() {
        return (String) get(11);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_location</code>.
     */
    public void setTemplateLocation(String value) {
        set(12, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_location</code>.
     */
    public String getTemplateLocation() {
        return (String) get(12);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_app</code>.
     */
    public void setTemplateApp(String value) {
        set(13, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_app</code>.
     */
    public String getTemplateApp() {
        return (String) get(13);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_version</code>.
     */
    public void setTemplateVersion(String value) {
        set(14, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_version</code>.
     */
    public String getTemplateVersion() {
        return (String) get(14);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_theme_templates.template_plugin</code>. The
     * plugin ID, if created by a plugin
     */
    public void setTemplatePlugin(ULong value) {
        set(15, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_theme_templates.template_plugin</code>. The
     * plugin ID, if created by a plugin
     */
    public ULong getTemplatePlugin() {
        return (ULong) get(15);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record16 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row16<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, ULong> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    @Override
    public Row16<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, ULong> valuesRow() {
        return (Row16) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_SET_ID;
    }

    @Override
    public Field<String> field3() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_GROUP;
    }

    @Override
    public Field<String> field4() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_CONTENT;
    }

    @Override
    public Field<String> field5() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_NAME;
    }

    @Override
    public Field<String> field6() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_DATA;
    }

    @Override
    public Field<Integer> field7() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_UPDATED;
    }

    @Override
    public Field<Integer> field8() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_REMOVABLE;
    }

    @Override
    public Field<Integer> field9() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_ADDED_TO;
    }

    @Override
    public Field<Integer> field10() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_USER_ADDED;
    }

    @Override
    public Field<Integer> field11() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_USER_EDITED;
    }

    @Override
    public Field<String> field12() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_MASTER_KEY;
    }

    @Override
    public Field<String> field13() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_LOCATION;
    }

    @Override
    public Field<String> field14() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_APP;
    }

    @Override
    public Field<String> field15() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_VERSION;
    }

    @Override
    public Field<ULong> field16() {
        return CoreThemeTemplates.CORE_THEME_TEMPLATES.TEMPLATE_PLUGIN;
    }

    @Override
    public Integer component1() {
        return getTemplateId();
    }

    @Override
    public Integer component2() {
        return getTemplateSetId();
    }

    @Override
    public String component3() {
        return getTemplateGroup();
    }

    @Override
    public String component4() {
        return getTemplateContent();
    }

    @Override
    public String component5() {
        return getTemplateName();
    }

    @Override
    public String component6() {
        return getTemplateData();
    }

    @Override
    public Integer component7() {
        return getTemplateUpdated();
    }

    @Override
    public Integer component8() {
        return getTemplateRemovable();
    }

    @Override
    public Integer component9() {
        return getTemplateAddedTo();
    }

    @Override
    public Integer component10() {
        return getTemplateUserAdded();
    }

    @Override
    public Integer component11() {
        return getTemplateUserEdited();
    }

    @Override
    public String component12() {
        return getTemplateMasterKey();
    }

    @Override
    public String component13() {
        return getTemplateLocation();
    }

    @Override
    public String component14() {
        return getTemplateApp();
    }

    @Override
    public String component15() {
        return getTemplateVersion();
    }

    @Override
    public ULong component16() {
        return getTemplatePlugin();
    }

    @Override
    public Integer value1() {
        return getTemplateId();
    }

    @Override
    public Integer value2() {
        return getTemplateSetId();
    }

    @Override
    public String value3() {
        return getTemplateGroup();
    }

    @Override
    public String value4() {
        return getTemplateContent();
    }

    @Override
    public String value5() {
        return getTemplateName();
    }

    @Override
    public String value6() {
        return getTemplateData();
    }

    @Override
    public Integer value7() {
        return getTemplateUpdated();
    }

    @Override
    public Integer value8() {
        return getTemplateRemovable();
    }

    @Override
    public Integer value9() {
        return getTemplateAddedTo();
    }

    @Override
    public Integer value10() {
        return getTemplateUserAdded();
    }

    @Override
    public Integer value11() {
        return getTemplateUserEdited();
    }

    @Override
    public String value12() {
        return getTemplateMasterKey();
    }

    @Override
    public String value13() {
        return getTemplateLocation();
    }

    @Override
    public String value14() {
        return getTemplateApp();
    }

    @Override
    public String value15() {
        return getTemplateVersion();
    }

    @Override
    public ULong value16() {
        return getTemplatePlugin();
    }

    @Override
    public CoreThemeTemplatesRecord value1(Integer value) {
        setTemplateId(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value2(Integer value) {
        setTemplateSetId(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value3(String value) {
        setTemplateGroup(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value4(String value) {
        setTemplateContent(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value5(String value) {
        setTemplateName(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value6(String value) {
        setTemplateData(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value7(Integer value) {
        setTemplateUpdated(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value8(Integer value) {
        setTemplateRemovable(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value9(Integer value) {
        setTemplateAddedTo(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value10(Integer value) {
        setTemplateUserAdded(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value11(Integer value) {
        setTemplateUserEdited(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value12(String value) {
        setTemplateMasterKey(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value13(String value) {
        setTemplateLocation(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value14(String value) {
        setTemplateApp(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value15(String value) {
        setTemplateVersion(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord value16(ULong value) {
        setTemplatePlugin(value);
        return this;
    }

    @Override
    public CoreThemeTemplatesRecord values(Integer value1, Integer value2, String value3, String value4, String value5, String value6, Integer value7, Integer value8, Integer value9, Integer value10, Integer value11, String value12, String value13, String value14, String value15, ULong value16) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreThemeTemplatesRecord
     */
    public CoreThemeTemplatesRecord() {
        super(CoreThemeTemplates.CORE_THEME_TEMPLATES);
    }

    /**
     * Create a detached, initialised CoreThemeTemplatesRecord
     */
    public CoreThemeTemplatesRecord(Integer templateId, Integer templateSetId, String templateGroup, String templateContent, String templateName, String templateData, Integer templateUpdated, Integer templateRemovable, Integer templateAddedTo, Integer templateUserAdded, Integer templateUserEdited, String templateMasterKey, String templateLocation, String templateApp, String templateVersion, ULong templatePlugin) {
        super(CoreThemeTemplates.CORE_THEME_TEMPLATES);

        setTemplateId(templateId);
        setTemplateSetId(templateSetId);
        setTemplateGroup(templateGroup);
        setTemplateContent(templateContent);
        setTemplateName(templateName);
        setTemplateData(templateData);
        setTemplateUpdated(templateUpdated);
        setTemplateRemovable(templateRemovable);
        setTemplateAddedTo(templateAddedTo);
        setTemplateUserAdded(templateUserAdded);
        setTemplateUserEdited(templateUserEdited);
        setTemplateMasterKey(templateMasterKey);
        setTemplateLocation(templateLocation);
        setTemplateApp(templateApp);
        setTemplateVersion(templateVersion);
        setTemplatePlugin(templatePlugin);
    }
}
