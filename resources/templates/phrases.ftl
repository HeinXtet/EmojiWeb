<#import "common/bootstrap.ftl" as b>
<@b.page>
    <div class="container">
              <h1>Welcome ${displayName}</h1>
                <#if phrases??>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Emoji</th>
                                <th>Phrases</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list phrases as phrase>
                            <tr>
                                <td>
                                    ${phrase.emoji}
                                </td>
                                <td>
                                    ${phrase.name}
                                </td>
                                <td>
                                    <form method="post" action="/api/v1/phrases">
                                        <input type="hidden" name="id" value="${phrase.id}" />
                                        <input type="hidden" name="action" value="delete" />
                                        <input type="image" src="/static/images/delete.png" width="24" height="24" border="0" alt="Delete" />
                                    </form>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </#if>
                <div class="panel-body">
                <form method="post" action="/api/v1/phrases">
                    <input type="hidden" name="action" value="add"/>
                    Emoji:<br>
                    <input type="text" name="emoji">
                    <input type="text" name="phrase">
                    <input type="submit" name="Submit">
                </form>
                </div>
    </div>
</@b.page>
