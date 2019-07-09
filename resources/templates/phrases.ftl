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
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </#if>
    </div>
</@b.page>
