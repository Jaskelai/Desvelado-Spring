<%@ attribute name="idCB" required="true" type="java.lang.String" description="Id for input" %>
<%@ attribute name="nameField" required="true" type="java.lang.String" description="Name for input" %>
<%@ attribute name="valueField" required="true" type="java.lang.String" description="Name for input" %>

<div class="wthree-text">
    <label class="anim">
        <input type="checkbox" name="${nameField}" id="${idCB}" class="checkbox">
        <span>${valueField}</span>
    </label>
    <div class="clear"></div>
</div>