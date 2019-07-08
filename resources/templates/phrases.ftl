<#import "common/bootstrap.ftl" as b>
<@b.page>
    <div class="container">
        <html>
            <body>
                <h1>${displayName}</h1>
                <ul>
                    <#list phrases as phrase>
                        <li>${phrase}</li>
                     </#list>
                </ul>
            </body>
        </html>
    </div>
</@b.page>
