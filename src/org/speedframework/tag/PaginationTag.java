/**
 *
 */
package org.speedframework.tag;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.speedframework.entity.*;

/**
 * <p>Title: SpeedFrameworkWork�־ò���</p>
 *
 * <p>Description: ���ڷ�ҳ��ǩ��</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SpeedFrameworkWork</p>
 * @author ��־�� �绰��13824431576
 * @version 1.1beta
 */
public class PaginationTag
    extends TagSupport {

  private static final long serialVersionUID = 1673491971683216709L;
  private String path = null;
  private String name = null;
  private String parameter = null;
  private String formName = null;


  public void setName(String name) {
    this.name = name;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public void setParameter(String parameter) {
    this.parameter = parameter;
  }


  public void setFormName(String formName) {
    this.formName = formName;
  }


  public int doStartTag() throws JspException {
    Pagination pagination = null;

    if (this.path == null) {
      throw new NullPointerException("path is null");
    }

    if (this.name == null) {
      this.name = "pagination";
    }

    if (this.parameter == null) {
      this.parameter = "page";
    }

    pagination = (Pagination) pageContext.getRequest().getAttribute(name);

    if (pagination == null) {
      throw new NullPointerException(name + "is null in request");
    }

    try {
      JspWriter out = pageContext.getOut();

      String contextPath = encodeURL(this.path, this.parameter);
      if (this.formName != null && this.formName.length() > 0) {
        if (pagination.isHasPreviousPage()) {
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      "1\" onclick=\"paginationSubmit('1');return false;\" title=\"First\">��ҳ</a>");
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getPreviousPage() +
                      "\" onclick=\"paginationSubmit('" +
                      pagination.getPreviousPage() +
                      "');return false;\" title=\"Previous\">��ҳ</a>");
        }
        else {
          out.println("<span title=\"First\">��ҳ</span>");
          out.println("<span title=\"Previous\">��ҳ</span>");
        }

        if (pagination.isHasNextPage()) {
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getNextPage() +
                      "\" onclick=\"paginationSubmit('" +
                      pagination.getNextPage() +
                      "');return false;\"  title=\"Next\">��ҳ</a>");
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getTotalPage() +
                      "\" onclick=\"paginationSubmit('" +
                      pagination.getTotalPage() +
                      "');return false;\" title=\"Last\">βҳ</a>");
        }
        else {
          out.println("<span title=\"Next\">��ҳ</span>");
          out.println("<span title=\"Last\">βҳ</span>");
        }
      }
      else {
        if (pagination.isHasPreviousPage()) {
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      "1\" title=\"First\">��ҳ</a>");
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getPreviousPage() + "\" title=\"Previous\">��ҳ</a>");
        }
        else {
          out.println("<span title=\"First\">��ҳ</span>");
          out.println("<span title=\"Previous\">��ҳ</span>");
        }

        if (pagination.isHasNextPage()) {
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getNextPage() + "\" title=\"Next\">��ҳ</a>");
          out.println("<a class=\"pagination\" href=\"" + contextPath +
                      pagination.getTotalPage() + "\" title=\"Last\">βҳ</a>");
        }
        else {
          out.println("<span title=\"Next\">��ҳ</span>");
          out.println("<span title=\"Last\">βҳ</span>");
        }
      }

      out.println("&nbsp;");
      out.println("�ܼ�¼����" + pagination.getTotalCount() + "��");
      out.print("&nbsp;");
      out.println("ÿҳ" + pagination.getCount() + "��");
      out.print("&nbsp;");
      out.println("��" + pagination.getTotalPage() + "ҳ");
      out.print("&nbsp;");
      out.println("��" + pagination.getCurrPage() + "ҳ");
      out.println("&nbsp;");
      out.println("��ת����<input type=\"text\" id=\"" + this.parameter +
                  "\" class=\"textF\" size=\"5\" maxlength=\"5\">"+"ҳ");
      out.println("&nbsp;");
      out.println("<button onclick=\"paginationGoto();\" style=\"border:1px ridge #d6edef;background-color:#ffffff\">��ת</button>");
      out.println("<br />");
      /*����CSS����*/
      createCSS(out);
      if (this.formName != null && this.formName.length() > 0) {
        /*����js����*/
        createJS(out, contextPath, pagination.getTotalPage());
      }
      else {
        createNoFormJS(out, this.path, pagination.getTotalPage());
      }
      out.println();
    }
    catch (Exception e) {
      throw new JspException(e);
    }

    return SKIP_BODY;
  }

  /* (non-Javadoc)
   * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
   */
  public int doEndTag() throws JspException {
    // TODO Auto-generated method stub
    return EVAL_PAGE;
  }

  /**
   * �ı�URLʹ���ܷ��ʵ�action��servlet��ȥ
   * @param href ��ҳ�洫����href
   * @param param
   * @param queryStr ��ѯ����
   * @return
   * @author
   */
  private final String encodeURL(String href, String param) {
    StringBuffer buffer = new StringBuffer(100);

    HttpServletRequest request =
        (HttpServletRequest)this.pageContext.getRequest();
    buffer.append(request.getContextPath() + "/" + href);

    int question = href.indexOf("?");
    if (question > 0) {
      buffer.append("&" + param + "=");
    }
    else {
      buffer.append("?" + param + "=");
    }

    return buffer.toString();
  }

  /**
   * ����js��������ύform����js�ύ
   * @param out
   * @param contextPath
   * @throws Exception
   * @author
   */
  private final void createJS(JspWriter out, String contextPath, int totalPage) throws
      Exception {
    out.println("<script language=\"javascript\">");
    out.println("function paginationSubmit(pageNum_) { ");
    out.println("  document." + formName + ".action=\'" + contextPath +
                "\' + pageNum_ + \'\';");
    out.println("  document." + formName + ".submit();");
    out.println("}");
    /*��ת����*/
    HttpServletRequest request =
        (HttpServletRequest)this.pageContext.getRequest();
    String contPath = request.getContextPath() + "/" + this.path;
    out.println("function paginationGoto() { ");
    out.println("  if(!controlNotNull(document.all." + this.parameter +
                ", '��תҳ��')) {");
    out.println("    return;");
    out.println("  }");
    out.println("  ");
    out.println("  if(!controlIsInt(document.all." + this.parameter +
                ", '��תҳ��')) {");
    out.println("    return;");
    out.println("  }");
    out.println("  ");
    out.println("  pageNum_ = document.all." + this.parameter + ".value");
    out.println("  if(pageNum_ > 0 && pageNum_ <= " + totalPage + ") {");
    out.println("    document." + formName + ".action=\'" + contPath + "&" +
                this.parameter + "=\' + pageNum_ + \'\';");
    out.println("    document." + formName + ".submit();");
    out.println("  } else { ");
    out.println("    alert(\"�������ҳ��������Χ\");");
    out.println("  }");
    out.println("}");
    out.println("</script>");
    out.println();
  }

  /**
   * ����û��formʱ��js
   * @param out
   * @param contextPath
   * @thros Exception
   * @author
   */
  private final void createNoFormJS(JspWriter out, String href, int totalPage) throws
      Exception {
    HttpServletRequest request =
        (HttpServletRequest)this.pageContext.getRequest();
    String contPath = request.getContextPath() + "/" + href;

    out.println("<script language=\"javascript\">");
    out.println("function paginationGoto() { ");
    out.println("  if(!controlNotNull(document.all." + this.parameter +
                ", '��תҳ��')) {");
    out.println("    return;");
    out.println("  }");
    out.println("  ");
    out.println("  if(!controlIsInt(document.all." + this.parameter +
                ", '��תҳ��')) {");
    out.println("    return;");
    out.println("  }");
    out.println("  ");
    out.println("  pageNum_ = document.all." + this.parameter + ".value");
    out.println("  if(pageNum_ > 0 && pageNum_ <= " + totalPage + ") {");
    out.println("    document.location.href=\'" + contPath + "&" +
                this.parameter + "=\' + pageNum_ + \'\';");
    out.println("  } else { ");
    out.println("    alert(\"�������ҳ��������Χ\");");
    out.println("  }");
    out.println("}");
    out.println("</script>");
    out.println();
  }

  /**
   * ����css����
   * @param out
   * @throws Exception
   * @author
   */
  private final void createCSS(JspWriter out) throws Exception {
    out.println("<style type=\"text/css\">");
    out.println(
        "a.pagination:visited { text-decoration: underline; color: #808080; } ");  
    out.println(
        "a.pagination:link { text-decoration: underline; color: #009999; } ");
    out.println(
        "a.pagination:hover { text-decoration: none; color: #006699; } ");
    out.println(
        "a.pagination:active { text-decoration: none; color: #FF0033; } ");
    out.println("</style>");
  }

}
