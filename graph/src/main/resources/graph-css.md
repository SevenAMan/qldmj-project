## node
```css
node {
    /*填充*/
    fill-mode: none /*
    none:根本没有填充。
    plain:默认,使用 fill-color 属性的第一个颜色填充
    */;
    fill-color: green;
    fill-image: url("/icon/end.png");

    /*阴影*/
    shadow-mode: unkow;
    shadow-color: green;
    shadow-offset: 1, 2;

    /*绘制元素形状的轮廓*/
    stroke-mode: none;
    /*
    none、
    plain(stroke-color 和 stroke-width 属性绘制形状轮廓)
    dots stroke-width 对应的点组成
    */
    stroke-width: 1;
    stroke-color: green;

    z-index: 1; /*越大越上*/

    text-background-mode: rounded-box; /*圆角矩形*/
    text-style: italic;
    /*
    italic 斜体
    bold 粗体
    bold-italic 粗斜体
    */

    text-alignment: center;
    /*
    center: 中心
    left right under above along 同线的方向
    */

    shape: circle;
    /* 
     circle 原型 box 矩形 rounded-box 圆角矩形
     diamond 菱形  cross 十字叉
     freeplane 线条
     
     edge： line cubic-curve三次曲线 blob 弹性斑点
     
    square-line L-square-line horizontal-square-line vertical-square-line arrow
    flow
    
     */

    arrow-shape: arrow;
    /* 
     arrow显示一个简单的箭头
     circle 圆形
     diamond 菱形
     image 图
     */
    arrow-size: 1,2 /* 箭头长度 */
}
```