/*
 * This file is generated by jOOQ.
 */
package me.aa07.paradise.taskdaemon.database.forums.tables.records;


import me.aa07.paradise.taskdaemon.database.forums.enums.CoreOauthClientsCoreOauthClients;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreOauthClientsOauthApiAccess;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreOauthClientsOauthPkce;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreOauthClientsOauthPrompt;
import me.aa07.paradise.taskdaemon.database.forums.enums.CoreOauthClientsOauthType;
import me.aa07.paradise.taskdaemon.database.forums.tables.CoreOauthClients;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record18;
import org.jooq.Row18;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreOauthClientsRecord extends UpdatableRecordImpl<CoreOauthClientsRecord> implements Record18<String, String, String, String, UInteger, UByte, UInteger, String, UByte, CoreOauthClientsOauthPrompt, UByte, UByte, String, CoreOauthClientsOauthType, CoreOauthClientsOauthPkce, UByte, CoreOauthClientsOauthApiAccess, CoreOauthClientsCoreOauthClients> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_client_id</code>. The
     * client ID
     */
    public void setOauthClientId(String value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_client_id</code>. The
     * client ID
     */
    public String getOauthClientId() {
        return (String) get(0);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_grant_types</code>.
     * Available grant types
     */
    public void setOauthGrantTypes(String value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_grant_types</code>.
     * Available grant types
     */
    public String getOauthGrantTypes() {
        return (String) get(1);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_redirect_uris</code>.
     * JSON-encoded array of Redirect URIs
     */
    public void setOauthRedirectUris(String value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_redirect_uris</code>.
     * JSON-encoded array of Redirect URIs
     */
    public String getOauthRedirectUris() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_client_secret</code>. The
     * hashed client secret or NULL for public clients
     */
    public void setOauthClientSecret(String value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_client_secret</code>. The
     * hashed client secret or NULL for public clients
     */
    public String getOauthClientSecret() {
        return (String) get(3);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_access_token_length</code>.
     * Number of hours access token lives for, or NULL for no limit
     */
    public void setOauthAccessTokenLength(UInteger value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_access_token_length</code>.
     * Number of hours access token lives for, or NULL for no limit
     */
    public UInteger getOauthAccessTokenLength() {
        return (UInteger) get(4);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_use_refresh_tokens</code>.
     * Boolean value indicating if refresh tokens should be used
     */
    public void setOauthUseRefreshTokens(UByte value) {
        set(5, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_use_refresh_tokens</code>.
     * Boolean value indicating if refresh tokens should be used
     */
    public UByte getOauthUseRefreshTokens() {
        return (UByte) get(5);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_refresh_token_length</code>.
     * Number of days refresh tokens live for, or NULL for no expiry or not
     * applicable
     */
    public void setOauthRefreshTokenLength(UInteger value) {
        set(6, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_refresh_token_length</code>.
     * Number of days refresh tokens live for, or NULL for no expiry or not
     * applicable
     */
    public UInteger getOauthRefreshTokenLength() {
        return (UInteger) get(6);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_scopes</code>.
     * JSON-encoded array of scope data
     */
    public void setOauthScopes(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_scopes</code>.
     * JSON-encoded array of scope data
     */
    public String getOauthScopes() {
        return (String) get(7);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_enabled</code>.
     * Boolean value indicating if client is enabled
     */
    public void setOauthEnabled(UByte value) {
        set(8, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_enabled</code>.
     * Boolean value indicating if client is enabled
     */
    public UByte getOauthEnabled() {
        return (UByte) get(8);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_prompt</code>.
     * How to prompt users. login = Users must always login. reauthorize = Users
     * are shown authorize screen. automatic = Users with an existing access
     * token skip authorise screen. none = Never show screen
     */
    public void setOauthPrompt(CoreOauthClientsOauthPrompt value) {
        set(9, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_prompt</code>.
     * How to prompt users. login = Users must always login. reauthorize = Users
     * are shown authorize screen. automatic = Users with an existing access
     * token skip authorise screen. none = Never show screen
     */
    public CoreOauthClientsOauthPrompt getOauthPrompt() {
        return (CoreOauthClientsOauthPrompt) get(9);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_choose_scopes</code>.
     * Boolean value indicating if user can choose which of the requested scopes
     * to grant
     */
    public void setOauthChooseScopes(UByte value) {
        set(10, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_choose_scopes</code>.
     * Boolean value indicating if user can choose which of the requested scopes
     * to grant
     */
    public UByte getOauthChooseScopes() {
        return (UByte) get(10);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_ucp</code>.
     * Boolean value indicating if the user can see and revoke their
     * authorization in their settings page
     */
    public void setOauthUcp(UByte value) {
        set(11, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_ucp</code>.
     * Boolean value indicating if the user can see and revoke their
     * authorization in their settings page
     */
    public UByte getOauthUcp() {
        return (UByte) get(11);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_brute_force</code>.
     * JSON-encoded array of IP addresses and failed authentication, for
     * brute-force prevention
     */
    public void setOauthBruteForce(String value) {
        set(12, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_brute_force</code>.
     * JSON-encoded array of IP addresses and failed authentication, for
     * brute-force prevention
     */
    public String getOauthBruteForce() {
        return (String) get(12);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_type</code>. A
     * hint for how to display the form (NULL is generic custom)
     */
    public void setOauthType(CoreOauthClientsOauthType value) {
        set(13, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_type</code>. A
     * hint for how to display the form (NULL is generic custom)
     */
    public CoreOauthClientsOauthType getOauthType() {
        return (CoreOauthClientsOauthType) get(13);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_pkce</code>.
     * Required PKCE method.
     */
    public void setOauthPkce(CoreOauthClientsOauthPkce value) {
        set(14, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_pkce</code>.
     * Required PKCE method.
     */
    public CoreOauthClientsOauthPkce getOauthPkce() {
        return (CoreOauthClientsOauthPkce) get(14);
    }

    /**
     * Setter for <code>paradise_forums.core_oauth_clients.oauth_graphql</code>.
     * A boolean indicating if this client (and access tokens issued by it) can
     * access the GraphQL API
     */
    public void setOauthGraphql(UByte value) {
        set(15, value);
    }

    /**
     * Getter for <code>paradise_forums.core_oauth_clients.oauth_graphql</code>.
     * A boolean indicating if this client (and access tokens issued by it) can
     * access the GraphQL API
     */
    public UByte getOauthGraphql() {
        return (UByte) get(15);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.oauth_api_access</code>.
     */
    public void setOauthApiAccess(CoreOauthClientsOauthApiAccess value) {
        set(16, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.oauth_api_access</code>.
     */
    public CoreOauthClientsOauthApiAccess getOauthApiAccess() {
        return (CoreOauthClientsOauthApiAccess) get(16);
    }

    /**
     * Setter for
     * <code>paradise_forums.core_oauth_clients.core_oauth_clients</code>.
     */
    public void setCoreOauthClients(CoreOauthClientsCoreOauthClients value) {
        set(17, value);
    }

    /**
     * Getter for
     * <code>paradise_forums.core_oauth_clients.core_oauth_clients</code>.
     */
    public CoreOauthClientsCoreOauthClients getCoreOauthClients() {
        return (CoreOauthClientsCoreOauthClients) get(17);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record18 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row18<String, String, String, String, UInteger, UByte, UInteger, String, UByte, CoreOauthClientsOauthPrompt, UByte, UByte, String, CoreOauthClientsOauthType, CoreOauthClientsOauthPkce, UByte, CoreOauthClientsOauthApiAccess, CoreOauthClientsCoreOauthClients> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    @Override
    public Row18<String, String, String, String, UInteger, UByte, UInteger, String, UByte, CoreOauthClientsOauthPrompt, UByte, UByte, String, CoreOauthClientsOauthType, CoreOauthClientsOauthPkce, UByte, CoreOauthClientsOauthApiAccess, CoreOauthClientsCoreOauthClients> valuesRow() {
        return (Row18) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_CLIENT_ID;
    }

    @Override
    public Field<String> field2() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_GRANT_TYPES;
    }

    @Override
    public Field<String> field3() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_REDIRECT_URIS;
    }

    @Override
    public Field<String> field4() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_CLIENT_SECRET;
    }

    @Override
    public Field<UInteger> field5() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_ACCESS_TOKEN_LENGTH;
    }

    @Override
    public Field<UByte> field6() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_USE_REFRESH_TOKENS;
    }

    @Override
    public Field<UInteger> field7() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_REFRESH_TOKEN_LENGTH;
    }

    @Override
    public Field<String> field8() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_SCOPES;
    }

    @Override
    public Field<UByte> field9() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_ENABLED;
    }

    @Override
    public Field<CoreOauthClientsOauthPrompt> field10() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_PROMPT;
    }

    @Override
    public Field<UByte> field11() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_CHOOSE_SCOPES;
    }

    @Override
    public Field<UByte> field12() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_UCP;
    }

    @Override
    public Field<String> field13() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_BRUTE_FORCE;
    }

    @Override
    public Field<CoreOauthClientsOauthType> field14() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_TYPE;
    }

    @Override
    public Field<CoreOauthClientsOauthPkce> field15() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_PKCE;
    }

    @Override
    public Field<UByte> field16() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_GRAPHQL;
    }

    @Override
    public Field<CoreOauthClientsOauthApiAccess> field17() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.OAUTH_API_ACCESS;
    }

    @Override
    public Field<CoreOauthClientsCoreOauthClients> field18() {
        return CoreOauthClients.CORE_OAUTH_CLIENTS.CORE_OAUTH_CLIENTS_;
    }

    @Override
    public String component1() {
        return getOauthClientId();
    }

    @Override
    public String component2() {
        return getOauthGrantTypes();
    }

    @Override
    public String component3() {
        return getOauthRedirectUris();
    }

    @Override
    public String component4() {
        return getOauthClientSecret();
    }

    @Override
    public UInteger component5() {
        return getOauthAccessTokenLength();
    }

    @Override
    public UByte component6() {
        return getOauthUseRefreshTokens();
    }

    @Override
    public UInteger component7() {
        return getOauthRefreshTokenLength();
    }

    @Override
    public String component8() {
        return getOauthScopes();
    }

    @Override
    public UByte component9() {
        return getOauthEnabled();
    }

    @Override
    public CoreOauthClientsOauthPrompt component10() {
        return getOauthPrompt();
    }

    @Override
    public UByte component11() {
        return getOauthChooseScopes();
    }

    @Override
    public UByte component12() {
        return getOauthUcp();
    }

    @Override
    public String component13() {
        return getOauthBruteForce();
    }

    @Override
    public CoreOauthClientsOauthType component14() {
        return getOauthType();
    }

    @Override
    public CoreOauthClientsOauthPkce component15() {
        return getOauthPkce();
    }

    @Override
    public UByte component16() {
        return getOauthGraphql();
    }

    @Override
    public CoreOauthClientsOauthApiAccess component17() {
        return getOauthApiAccess();
    }

    @Override
    public CoreOauthClientsCoreOauthClients component18() {
        return getCoreOauthClients();
    }

    @Override
    public String value1() {
        return getOauthClientId();
    }

    @Override
    public String value2() {
        return getOauthGrantTypes();
    }

    @Override
    public String value3() {
        return getOauthRedirectUris();
    }

    @Override
    public String value4() {
        return getOauthClientSecret();
    }

    @Override
    public UInteger value5() {
        return getOauthAccessTokenLength();
    }

    @Override
    public UByte value6() {
        return getOauthUseRefreshTokens();
    }

    @Override
    public UInteger value7() {
        return getOauthRefreshTokenLength();
    }

    @Override
    public String value8() {
        return getOauthScopes();
    }

    @Override
    public UByte value9() {
        return getOauthEnabled();
    }

    @Override
    public CoreOauthClientsOauthPrompt value10() {
        return getOauthPrompt();
    }

    @Override
    public UByte value11() {
        return getOauthChooseScopes();
    }

    @Override
    public UByte value12() {
        return getOauthUcp();
    }

    @Override
    public String value13() {
        return getOauthBruteForce();
    }

    @Override
    public CoreOauthClientsOauthType value14() {
        return getOauthType();
    }

    @Override
    public CoreOauthClientsOauthPkce value15() {
        return getOauthPkce();
    }

    @Override
    public UByte value16() {
        return getOauthGraphql();
    }

    @Override
    public CoreOauthClientsOauthApiAccess value17() {
        return getOauthApiAccess();
    }

    @Override
    public CoreOauthClientsCoreOauthClients value18() {
        return getCoreOauthClients();
    }

    @Override
    public CoreOauthClientsRecord value1(String value) {
        setOauthClientId(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value2(String value) {
        setOauthGrantTypes(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value3(String value) {
        setOauthRedirectUris(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value4(String value) {
        setOauthClientSecret(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value5(UInteger value) {
        setOauthAccessTokenLength(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value6(UByte value) {
        setOauthUseRefreshTokens(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value7(UInteger value) {
        setOauthRefreshTokenLength(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value8(String value) {
        setOauthScopes(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value9(UByte value) {
        setOauthEnabled(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value10(CoreOauthClientsOauthPrompt value) {
        setOauthPrompt(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value11(UByte value) {
        setOauthChooseScopes(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value12(UByte value) {
        setOauthUcp(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value13(String value) {
        setOauthBruteForce(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value14(CoreOauthClientsOauthType value) {
        setOauthType(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value15(CoreOauthClientsOauthPkce value) {
        setOauthPkce(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value16(UByte value) {
        setOauthGraphql(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value17(CoreOauthClientsOauthApiAccess value) {
        setOauthApiAccess(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord value18(CoreOauthClientsCoreOauthClients value) {
        setCoreOauthClients(value);
        return this;
    }

    @Override
    public CoreOauthClientsRecord values(String value1, String value2, String value3, String value4, UInteger value5, UByte value6, UInteger value7, String value8, UByte value9, CoreOauthClientsOauthPrompt value10, UByte value11, UByte value12, String value13, CoreOauthClientsOauthType value14, CoreOauthClientsOauthPkce value15, UByte value16, CoreOauthClientsOauthApiAccess value17, CoreOauthClientsCoreOauthClients value18) {
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
        value17(value17);
        value18(value18);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreOauthClientsRecord
     */
    public CoreOauthClientsRecord() {
        super(CoreOauthClients.CORE_OAUTH_CLIENTS);
    }

    /**
     * Create a detached, initialised CoreOauthClientsRecord
     */
    public CoreOauthClientsRecord(String oauthClientId, String oauthGrantTypes, String oauthRedirectUris, String oauthClientSecret, UInteger oauthAccessTokenLength, UByte oauthUseRefreshTokens, UInteger oauthRefreshTokenLength, String oauthScopes, UByte oauthEnabled, CoreOauthClientsOauthPrompt oauthPrompt, UByte oauthChooseScopes, UByte oauthUcp, String oauthBruteForce, CoreOauthClientsOauthType oauthType, CoreOauthClientsOauthPkce oauthPkce, UByte oauthGraphql, CoreOauthClientsOauthApiAccess oauthApiAccess, CoreOauthClientsCoreOauthClients coreOauthClients) {
        super(CoreOauthClients.CORE_OAUTH_CLIENTS);

        setOauthClientId(oauthClientId);
        setOauthGrantTypes(oauthGrantTypes);
        setOauthRedirectUris(oauthRedirectUris);
        setOauthClientSecret(oauthClientSecret);
        setOauthAccessTokenLength(oauthAccessTokenLength);
        setOauthUseRefreshTokens(oauthUseRefreshTokens);
        setOauthRefreshTokenLength(oauthRefreshTokenLength);
        setOauthScopes(oauthScopes);
        setOauthEnabled(oauthEnabled);
        setOauthPrompt(oauthPrompt);
        setOauthChooseScopes(oauthChooseScopes);
        setOauthUcp(oauthUcp);
        setOauthBruteForce(oauthBruteForce);
        setOauthType(oauthType);
        setOauthPkce(oauthPkce);
        setOauthGraphql(oauthGraphql);
        setOauthApiAccess(oauthApiAccess);
        setCoreOauthClients(coreOauthClients);
    }
}
