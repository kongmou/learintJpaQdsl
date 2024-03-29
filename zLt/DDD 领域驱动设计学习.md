[TOC]



# DDD 领域驱动设计学习

## 2.限界上下文

### 2.1 Eric Evans的DDD奠基之作《Domain-Driven Design》

​	思想： **设计观念的转变，蕴含了全新的设计思想、设计原则与设计过程。**DDD不是架构（设计）方法，因此不能把每个设计细节具象化。

Eric的DDD其实没有解决三个问题：

1. 如何进行领域建模
2. 如何识别Bounded Context
3. 如何在战术层面寻找对象

###  2.2战略建模与战术建模

| **DDD的战略建模与战术建模**                                  |                                                              |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| 战略建模-Strategic Modeling                                  | 战术建模-Tactical Modeling：                                 |
| 1.  限界上下文（Bounded Context）<br /> 2.上下文映射图（Context Mapping） | 1.  聚合-Aggregate<br /> 2. 实体-Entity <br /> 3. 值对象-Value Objects <br /> 4. 资源库-Repository <br />  5.领域服务-Domain Services<br />  6. 领域事件-Domain Events <br />  7.模块-Modules |



####  2.2.1 Bound Context（BC）

[DDD分层架构的三种模式](https://www.jianshu.com/p/a775836c7e25)：

> UL（Ubiquitous Language，通用语言）是团队共享的语言，是DDD中最具威力的特性之一。不管你在团队中的角色如何，只要你是团队的一员，你都将使用UL。由于UL的重要性，所以需要让每个概念在各自的上下文中是清晰无歧义的，于是DDD在战略设计上提出了模式BC（Bounded Context，限界上下文）。UL和BC同时构成了DDD的两大支柱，并且它们是相辅相成的，即UL都有其确定的上下文含义，而BC中的每个概念都有唯一的含义。
> 一个业务领域划分成若干个BC，它们之间通过Context Map进行集成。BC是一个显式的边界，领域模型便存在于这个边界之内。领域模型是关于某个特定业务领域的软件模型。通常，领域模型通过对象模型来实现，这些对象同时包含了数据和行为，并且表达了准确的业务含义。
> 从广义上来讲，领域即是一个组织所做的事情以及其中所包含的一切，表示整个业务系统。由于“领域模型”包含了“领域”这个词，我们可能会认为应该为整个业务系统创建一个单一的、内聚的和全功能式的模型。然而，这并不是我们使用DDD的目标。正好相反，领域模型存在于BC内。

关于BC，有一个很形象的类别，细胞和细胞膜的类比：

![img](https://upload-images.jianshu.io/upload_images/8906859-f290e482ffc34e35.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

细胞之所以能存在，是因为细胞膜定义了什么在细胞内，什么在细胞外，而且确认了什么物质可以通过细胞膜

----

BC → 细胞膜

**大型系统领域模型的完全统一是不可行的，也不是一种经济有效的方法。**→ **保持一致，不受外界干扰**

BC明确地限定了模型的应用范围，以便让团队成员对什么应该保持一致以及上下文之间如何关联有一个明确和共同的理解。在 CONTEXT中，要保证模型在逻辑上统一，而不用考虑它是不是适用于边界之外的情况。



####  2.2.2 CONTINUOUS INTEGRATION（持续集成-CI）

​	定义BC→持续合理化  → 多人使用，导致分类 →**使用CI**

​	**CI的两个操作：**

1. 模型概念的集成
2. 实现的集成

**代码和其他工作合并 → 持续集成，坚持使用UL（多加锤炼） →暴露分裂 →解决达成共识  。**最后，不要在持续集成中做一些不必要的工作。CI只有在BC中才是重要的。相邻 CONTEXT中的设计问题（包括转换）不必以同一个步调来处理。



####  2.2.3 上下文图（Context Map）

多系统交互，避免混乱，建立一个所有模型上下文的全局视图。CM:各个系统之间的关系的总体视图

**过程**：建模的过程需要识别每个模型在项目中的作用，并定义其 BC，这包括非面向对象子系统的隐含模型。为每个 BC命名，并把名称添加到 UL。描述模型之间的接触点，明确每次交流所需的转换，并突出任何共享的内容。画出现有的范围。为稍后的转换做好准备。
在Context Map中可以有如下几种形式来表征限界上下文之间的关系，简介如下：

1. 共享内核（Shared Kernel）：提取公共部分
2. 客户/供应商（Customer/Supplier）：不同系统之间存在依赖关系时，下游系统依赖上游系统，下游系统是客户，上游系统是供应商，双方协定好需求，由上游系统完成模型的构建和开发，并交付给下游系统使用，之后进行联调、测试。这种模式建立在团队之间友好合作和支持的情况下。
3. Conformist（追随者）： 单方面依赖
4.  防腐层（Anticorruption Layer）：转换层
5. 公开主机服务（Open Host Service）：当一个子系统必须与大量其他系统进行集成时，为每个集成都定制一个转换层可能会减慢团队的工作速度。如果一个子系统有某种内聚性，那么或许可以把它描述为一组 Service,这组 Service满足了其他子系统的公共需求。公开主机服务（Open Host Service）能够允许系统将一组Service公开出去公其他系统访问。定义一个协议，把你的子系统作为一组 Service供其他系统访问。开放这个协议，以便所有需要与你的子系统集成的人都可以使用它。当有新的集成需求时，就增强并扩展这个协议，但个别团队的特殊需求除外。
6. 各行其道（Separate Way）：当两个系统之间的关系并非必不可少时，两者完全可以彼此独立，各自独立建模，独立发展，互不影响

## Context Map例子

![Context Map](https://upload-images.jianshu.io/upload_images/8906859-50008d99237af894.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/577/format/webp)

<center>例子</center>

> U表示上游（Upstream）的被依赖方，D表示下游（Downstream）的依赖方。防腐层（ACL）放在下游，将上游的消息转化为下游的领域模型。

----

# 模式图谱





![模式图谱](https://upload-images.jianshu.io/upload_images/8906859-69cb54a3fe068b58.png?imageMogr2/auto-orient/)

<center>模式图谱</center>







![构造块](https://raw.githubusercontent.com/kongmou/learintJpaQdsl/master/%E6%9E%84%E9%80%A0%E5%9D%97.png)

