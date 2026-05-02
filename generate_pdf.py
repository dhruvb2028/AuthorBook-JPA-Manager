import os
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, PageBreak, Preformatted, Image
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from svglib.svglib import svg2rlg

def create_pdf():
    pdf_path = "Submission.pdf"
    doc = SimpleDocTemplate(pdf_path, pagesize=letter, rightMargin=40, leftMargin=40, topMargin=40, bottomMargin=40)
    styles = getSampleStyleSheet()
    
    title_style = styles['Heading1']
    title_style.alignment = 1
    heading_style = styles['Heading2']
    body_style = styles['Normal']
    
    code_style = ParagraphStyle(
        'CodeStyle',
        parent=styles['Code'],
        fontSize=8,
        leading=10,
        backColor='#f4f4f4',
        borderPadding=5
    )
    
    story = []
    
    # Title
    story.append(Paragraph("Library Management System - Database Assignment", title_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # 1. ER Design
    story.append(Paragraph("1. Entity Relationship Design", heading_style))
    er_text = "An Author can have multiple Books (One-to-Many), and each Book belongs to one Author (Many-to-One). This relationship is strictly enforced using JPA's @OneToMany and @ManyToOne annotations."
    story.append(Paragraph(er_text, body_style))
    story.append(Spacer(1, 0.1 * inch))
    
    svg_path = "er_diagram.svg"
    if os.path.exists(svg_path):
        try:
            drawing = svg2rlg(svg_path)
            if drawing:
                drawing.scale(0.7, 0.7)
                story.append(drawing)
        except Exception:
            pass
    story.append(Spacer(1, 0.2 * inch))
    
    # 2. Implementation Details
    story.append(Paragraph("2. Implementation Details (Code)", heading_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # a) Populate DB
    story.append(Paragraph("<b>a) Populate Database</b>", body_style))
    story.append(Spacer(1, 0.05 * inch))
    code_pop = """@PostConstruct
@Transactional
public void populateDatabase() {
    if (authorRepository.count() == 0) {
        for (int i = 1; i <= 10; i++) {
            Author author = new Author("Author " + i, "Nationality " + i);
            authorRepository.save(author);
            Book book = new Book("Book Title " + i, "Genre " + i, author);
            bookRepository.save(book);
        }
    }
}"""
    story.append(Preformatted(code_pop, code_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # b) Create Operation
    story.append(Paragraph("<b>b) Create Operation</b>", body_style))
    story.append(Spacer(1, 0.05 * inch))
    code_create = """@PostMapping("/addBook")
public String addBook(@ModelAttribute("book") Book book, Model model) {
    try {
        libraryService.saveBook(book);
        return "redirect:/";
    } catch (Exception e) {
        model.addAttribute("error", "Error adding book: " + e.getMessage());
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "addBook";
    }
}"""
    story.append(Preformatted(code_create, code_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # c) Read Operation
    story.append(Paragraph("<b>c) Read Operation</b>", body_style))
    story.append(Spacer(1, 0.05 * inch))
    code_read = """// BookRepository.java (Custom inner join)
@Query("SELECT b FROM Book b JOIN b.author a")
List<Book> findAllBooksWithAuthors();

// index.jsp (Iterating over entities)
<c:forEach var="book" items="${books}">
    <tr>
        <td>${book.id}</td>
        <td>${book.title}</td>
        <td>${book.genre}</td>
        <td>${book.author.name}</td>
    </tr>
</c:forEach>"""
    story.append(Preformatted(code_read, code_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # d) Update Operation
    story.append(Paragraph("<b>d) Update Operation</b>", body_style))
    story.append(Spacer(1, 0.05 * inch))
    code_update = """@GetMapping("/updateBook/{id}")
public String showUpdateBookForm(@PathVariable("id") Long id, Model model) {
    Book book = libraryService.getBookById(id);
    model.addAttribute("book", book);
    model.addAttribute("authors", libraryService.getAllAuthors());
    return "updateBook";
}"""
    story.append(Preformatted(code_update, code_style))
    story.append(Spacer(1, 0.4 * inch))
    
    # 3. Screenshots
    story.append(PageBreak())
    story.append(Paragraph("3. Screenshots", heading_style))
    story.append(Spacer(1, 0.2 * inch))
    
    screenshots = [
        'screenshots/image.png', 
        'screenshots/image-1.png', 
        'screenshots/image-2.png', 
        'screenshots/image-4.png', 
        'screenshots/image-3.png'
    ]
    for img_path in screenshots:
        if os.path.exists(img_path):
            try:
                from reportlab.lib.utils import ImageReader
                img_reader = ImageReader(img_path)
                iw, ih = img_reader.getSize()
                target_width = 6.0 * inch
                target_height = target_width * (ih / float(iw))
                img = Image(img_path, width=target_width, height=target_height)
                story.append(img)
                story.append(Spacer(1, 0.3 * inch))
            except Exception as e:
                story.append(Paragraph(f"[Error loading {img_path}: {e}]", body_style))
    
    story.append(PageBreak())
    
    # 4. Challenges
    story.append(Paragraph("4. Challenges Faced & Solutions", heading_style))
    story.append(Spacer(1, 0.15 * inch))
    challenges = """
    <b>Challenge 1: JSP Resolution in Spring Boot 3</b><br/>
    <i>Issue:</i> Spring Boot 3 migrated to Jakarta EE, making traditional javax.servlet JSTL dependencies incompatible.<br/>
    <i>Solution:</i> Updated dependencies to jakarta.servlet.jsp.jstl-api and configured the view resolver.<br/><br/>
    
    <b>Challenge 2: Exception Handling for Constraints</b><br/>
    <i>Issue:</i> Invalid data submissions caused the application to crash completely.<br/>
    <i>Solution:</i> Implemented global exception handling using @ExceptionHandler to gracefully catch exceptions.<br/><br/>
    
    <b>Challenge 3: Inner Join in Spring Data JPA</b><br/>
    <i>Issue:</i> Fetching Books with their Authors natively could lead to N+1 query problems.<br/>
    <i>Solution:</i> Explicitly wrote an inner join using a custom JPQL @Query to optimize retrieval.
    """
    story.append(Paragraph(challenges, body_style))
    story.append(Spacer(1, 0.2 * inch))
    
    # 5. Github URL
    story.append(Paragraph("5. Github URL", heading_style))
    story.append(Paragraph("https://github.com/dhruvb2028/AuthorBook-JPA-Manager", body_style))
    
    doc.build(story)

if __name__ == "__main__":
    create_pdf()
    print("PDF Generated successfully.")
