class Question:
    def __init__(self, questionTitle, questionCategory, options, correctOption):

        self.questionTitle = questionTitle
        self.questionCategory = questionCategory
        self.options = options
        self.correctOption = correctOption

    def to_dict(self):
        return self.__dict__
