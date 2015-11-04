<#import "/lib/elements.ftl" as elements>
  <#if elementType == 'text'>
    <@elements.displayElementText element />
  <#elseif elementType == 'video'>
    <@elements.displayElementVideo element />
  <#elseif elementType == 'audio'>
    <@elements.displayElementAudio element />
    <#elseif elementType == 'file'>
      <@elements.displayElementFile element />
      <#elseif elementType == 'test'>
        <@elements.displayElementTest element />
          <#else>
              Nieznany typ elementu. From "display-element.ftl"
  </#if>
